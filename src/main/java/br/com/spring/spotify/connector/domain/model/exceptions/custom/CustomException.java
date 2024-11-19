package br.com.spring.spotify.connector.domain.model.exceptions.custom;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}