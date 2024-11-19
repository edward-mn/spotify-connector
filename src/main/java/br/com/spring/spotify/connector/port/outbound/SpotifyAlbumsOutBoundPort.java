package br.com.spring.spotify.connector.port.outbound;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface SpotifyAlbumsOutBoundPort {
    ResponseEntity<SpotifyAlbumDataOutput> getAlbums(String albumId, Map<String, String> headers);
    ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(List<String> albumsId, Map<String, String> headers);
}
