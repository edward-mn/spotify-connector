package br.com.spring.spotify.connector.port.inbound;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SpotifyAlbumsInBoundPort {
    ResponseEntity<Object> getAlbums(String albumId, Map<String, String> headers);
}
