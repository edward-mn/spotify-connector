package br.com.spring.spotify.connector.port.outbound;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SpotifyAlbumsOutBoundPort {
    ResponseEntity<SpotifyAlbumDataOutput> getAlbums(String albumId, Map<String, String> headers);
}
