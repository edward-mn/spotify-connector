package br.com.spring.spotify.connector.domain.usecase;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyDataOutput;
import org.springframework.http.ResponseEntity;

public interface UseCaseSpotifyConnectorGetAlbumsById {
    ResponseEntity<SpotifyDataOutput> getAlbums(String albumId);
}