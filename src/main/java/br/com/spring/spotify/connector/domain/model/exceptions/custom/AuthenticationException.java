package br.com.spring.spotify.connector.domain.model.exceptions.custom;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}
