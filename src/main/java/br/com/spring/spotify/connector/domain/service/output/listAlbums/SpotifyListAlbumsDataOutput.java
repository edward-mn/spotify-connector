package br.com.spring.spotify.connector.domain.service.output.listAlbums;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;

import java.util.List;

public record SpotifyListAlbumsDataOutput(
        List<SpotifyAlbumDataOutput> albums
) {
}
