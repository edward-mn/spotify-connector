package br.com.spring.spotify.connector.config.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationConfig {

    @Value("${webclient.spotify.grantType}")
    private String grantType;

    @Value("${webclient.spotify.credentials.clientId}")
    private String clientId;

    @Value("${webclient.spotify.credentials.clientSecret}")
    private String clientSecret;

    public String getGrantType() { return grantType; }

    public String getClientId() { return clientId; }

    public String getClientSecret() { return clientSecret; }
}

