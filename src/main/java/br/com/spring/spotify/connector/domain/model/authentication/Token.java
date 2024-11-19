package br.com.spring.spotify.connector.domain.model.authentication;

public record Token(
        String access_token,
        String token_type,
        int expires_in
) {
}
