package br.com.spring.spotify.connector.domain.usecase;

import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UseCaseSpotifyConnectorGetListAlbumsById {
    ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(List<String> albumsId);
}