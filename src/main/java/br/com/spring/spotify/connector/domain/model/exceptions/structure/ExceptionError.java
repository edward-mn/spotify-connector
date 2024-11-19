package br.com.spring.spotify.connector.domain.model.exceptions.structure;

import java.util.List;

public record ExceptionError(
        List<Errors> errors,
        Meta meta
) {
}