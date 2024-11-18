package br.com.spring.spotify.connector.domain.service.output.albums;

import java.util.List;

public record Tracks(
        int total,
        List<TrackItems> items
) {
}
