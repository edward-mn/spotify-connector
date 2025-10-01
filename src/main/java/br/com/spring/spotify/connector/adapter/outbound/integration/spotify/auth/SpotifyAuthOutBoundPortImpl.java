package br.com.spring.spotify.connector.adapter.outbound.integration.spotify.auth;

import br.com.spring.spotify.connector.config.authentication.SpotifyAuthProperties;
import br.com.spring.spotify.connector.domain.model.authentication.Token;
import br.com.spring.spotify.connector.domain.model.exceptions.statusCode.HandleStatusCodeException;
import br.com.spring.spotify.connector.port.outbound.SpotifyAuthOutBoundPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class SpotifyAuthOutBoundPortImpl implements SpotifyAuthOutBoundPort {

    private final SpotifyAuthProperties spotifyAuthProperties;
    private final WebClient spotifyWebClient;
    private final HandleStatusCodeException handleStatusCodeException;

    @Override
    public Token getAppToken() {
        String credentials = spotifyAuthProperties.getClientId() + ":" + spotifyAuthProperties.getClientSecret();
        String basicAuth = Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        return spotifyWebClient.post()
                .uri(spotifyAuthProperties.getTokenPath())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + basicAuth)
                .body(BodyInserters.fromFormData("grant_type", spotifyAuthProperties.getGrantType()))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> handleStatusCodeException.handleResponseStatus(response.statusCode().value())
                ).bodyToMono(Token.class)
                .block();
    }
}
