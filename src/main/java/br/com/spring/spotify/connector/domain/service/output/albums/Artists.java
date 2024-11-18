package br.com.spring.spotify.connector.domain.service.output.albums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Artists(
        ExternalSpotifyUrls externalUrls,
        String uri,
        String name,
        String type
) {
}
