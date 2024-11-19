package br.com.spring.spotify.connector.domain.model.exceptions.statusCode;

import org.springframework.http.HttpStatus;

public enum ExceptionStatusCode {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    UNPROCESSABLE_ENTITY(422),
    INTERNAL_ERROR(500);

    public int value;

    ExceptionStatusCode(int value) {
        this.value = value;
    }
}

