package br.com.spring.spotify.connector.port.outbound;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpotifyAlbumsOutBoundPort {
    ResponseEntity<SpotifyAlbumDataOutput> getAlbums(String albumId);
    ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(List<String> albumsId);
}
