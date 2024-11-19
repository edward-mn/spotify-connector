package br.com.spring.spotify.connector.domain.model.exceptions.structure;

public record Errors(
        String code,
        String title,
        String detail
) {
}