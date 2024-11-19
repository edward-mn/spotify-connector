package br.com.spring.spotify.connector.domain.service.output.albums;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SpotifyAlbumDataOutput(
        String id,
        String href,
        String name,
        String releaseDate,
        List<Artists> artists,
        Tracks tracks,
        List<CopyRights> copyrights,
        List<String> genres,
        Integer popularity
) {
}
