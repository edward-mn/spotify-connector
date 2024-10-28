package br.com.spring.spotify.connector.domain.model.exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

@Component
public class HandleStatusCodeException {

    public Mono<? extends Throwable> handleResponseStatus(int statusCode) {
        return switch (statusCode) {
            case 400 -> Mono.error(new BadRequestException("Bad request made"));
            case 401, 403 -> Mono.error(new AuthenticationException("Auth error"));
            case 404 -> Mono.error(new BadRequestException("Maybe not an error?"));
            case 500 -> Mono.error(new Exception("Server error"));
            default -> Mono.error(new Exception("Something went wrong"));
        };
    }
}
