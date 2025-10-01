package br.com.spring.spotify.connector.domain.service.authentication;

import br.com.spring.spotify.connector.config.authentication.SpotifyAuthProperties;
import br.com.spring.spotify.connector.domain.model.authentication.Token;
import br.com.spring.spotify.connector.port.outbound.SpotifyAuthOutBoundPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

@Service
@RequiredArgsConstructor
public class SpotifyTokenProvider {
    private final SpotifyAuthOutBoundPort spotifyAuth;
    private final SpotifyAuthProperties spotifyProperties;

    private final ReentrantLock lock = new ReentrantLock();

    private volatile Token cachedToken;
    private volatile Instant cachedTokenExpiry;

    public String getAccessTokenValue() {
        ensureValid();
        return cachedToken.access_token();
    }

    public String getAuthorizationHeader() {
        ensureValid();
        return cachedToken.token_type() + " " + cachedToken.access_token();
    }

    public void clearCache() {
        cachedToken = null;
        cachedTokenExpiry = null;
    }

    private void ensureValid() {
        if (isExpired()) {
            refresh();
        }
    }

    private boolean isExpired() {
        Instant exp = cachedTokenExpiry;
        return cachedToken == null || exp == null || Instant.now().isAfter(exp);
    }

    private void refresh() {
        if (!lock.tryLock()) return;
        try {
            if (isExpired()) {
                final Token fresh = spotifyAuth.getAppToken();

                final int skew = Math.max(0, spotifyProperties.getEarlySkewSeconds());
                final long validFor = Math.max(0L, (long) fresh.expires_in() - skew);

                cachedToken = fresh;
                cachedTokenExpiry = Instant.now().plusSeconds(validFor);
            }
        } finally {
            lock.unlock();
        }
    }

}
