package br.com.spring.spotify.connector.domain.model.exceptions.custom;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message) {
        super(message);
    }
}