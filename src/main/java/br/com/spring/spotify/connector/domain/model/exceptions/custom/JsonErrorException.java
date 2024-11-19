package br.com.spring.spotify.connector.domain.model.exceptions.custom;

public class JsonErrorException extends RuntimeException {
    public JsonErrorException(String message) {
        super(message);
    }
}