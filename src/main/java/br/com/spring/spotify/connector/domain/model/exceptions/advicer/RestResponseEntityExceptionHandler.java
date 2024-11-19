package br.com.spring.spotify.connector.domain.model.exceptions.advicer;

import br.com.spring.spotify.connector.domain.model.exceptions.custom.*;
import br.com.spring.spotify.connector.domain.model.exceptions.statusCode.ExceptionStatusCode;
import br.com.spring.spotify.connector.domain.model.exceptions.structure.Errors;
import br.com.spring.spotify.connector.domain.model.exceptions.structure.ExceptionError;
import br.com.spring.spotify.connector.domain.model.exceptions.structure.Meta;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionError> handleBadRequest(BadRequestException ex) {
        return buildErrorResponse(ExceptionStatusCode.BAD_REQUEST.value, "BadRequestException", ex);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionError> handleAuthentication(AuthenticationException ex) {
        return buildErrorResponse(ExceptionStatusCode.UNAUTHORIZED.value, "AuthenticationException", ex);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionError> handleNotFound(NotFoundException ex) {
        return buildErrorResponse(ExceptionStatusCode.NOT_FOUND.value, "NotFoundException", ex);
    }

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ExceptionError> handleServerError(ServerErrorException ex) {
        return buildErrorResponse(ExceptionStatusCode.INTERNAL_ERROR.value, "ServerErrorException", ex);
    }

    @ExceptionHandler(JsonErrorException.class)
    public ResponseEntity<ExceptionError> jsonException (JsonErrorException ex){
        return buildErrorResponse(ExceptionStatusCode.INTERNAL_ERROR.value, "jsonExceptionMsg", ex);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionError> handleCustomException(CustomException ex) {
        return buildErrorResponse(ExceptionStatusCode.INTERNAL_ERROR.value, "CustomException", ex);
    }

    private ResponseEntity<ExceptionError> buildErrorResponse(Integer statusCode, String description, RuntimeException msg) {
        Errors errors = new Errors(
                String.valueOf(statusCode),
                description,
                msg.toString()
        );

        ExceptionError exceptionError = new ExceptionError(
                Collections.singletonList(errors),
                new Meta(LocalDateTime.now().toString())
        );

        return ResponseEntity
                .status(HttpStatusCode.valueOf(statusCode))
                .body(exceptionError);
    }
}

