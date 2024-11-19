package br.com.spring.spotify.connector.domain.model.exceptions.statusCode;

import br.com.spring.spotify.connector.domain.model.exceptions.custom.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class HandleStatusCodeException {

    public Mono<? extends Throwable> handleResponseStatus(int statusCode) {
        return switch (statusCode) {
            case 400 -> Mono.error(new BadRequestException("Bad request made"));
            case 401, 403 -> Mono.error(new AuthenticationException("Authentication or authorization error"));
            case 404 -> Mono.error(new NotFoundException("Resource not found"));
            case 500 -> Mono.error(new ServerErrorException("Internal server error"));
            default -> Mono.error(new CustomException("Unexpected error with status code: " + statusCode));
        };
    }
}