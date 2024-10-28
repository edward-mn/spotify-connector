package br.com.spring.spotify.connector.port.outbound;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SpotifyAlbumsOutBoundPort {
    ResponseEntity<Object> getAlbums(String albumId, Map<String, String> headers);
}
