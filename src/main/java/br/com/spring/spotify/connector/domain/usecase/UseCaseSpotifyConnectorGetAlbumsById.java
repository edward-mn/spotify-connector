package br.com.spring.spotify.connector.domain.usecase;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import org.springframework.http.ResponseEntity;

public interface UseCaseSpotifyConnectorGetAlbumsById {
    ResponseEntity<SpotifyAlbumDataOutput> getAlbums(String albumId);
}