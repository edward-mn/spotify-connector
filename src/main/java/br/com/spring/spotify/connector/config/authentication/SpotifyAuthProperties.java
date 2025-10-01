package br.com.spring.spotify.connector.config.authentication;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "spotify.auth")
public class SpotifyAuthProperties {

    @Value("${spotify.auth.base-url}")
    private String baseUrl;

    @Value("${spotify.auth.token-path}")
    private String tokenPath;

    @Value("${spotify.secrets.grant-type}")
    private String grantType;

    @Value("${spotify.secrets.client-id}")
    private String clientId;

    @Value("${spotify.secrets.client-secret}")
    private String clientSecret;

    @Value("${spotify.secrets.early-skew-seconds}")
    private int earlySkewSeconds;
}

