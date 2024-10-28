package br.com.spring.spotify.connector.adapter.outbound.integration.spotify;

import br.com.spring.spotify.connector.adapter.outbound.integration.webclient.resources.WebClientAlbums;
import br.com.spring.spotify.connector.port.outbound.SpotifyAlbumsOutBoundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class SpotifyAlbumsOutBoundPortImpl implements SpotifyAlbumsOutBoundPort {

    @Autowired
    private WebClientAlbums webClientAlbums;

    @Override
    public ResponseEntity<Object> getAlbums(String albumId, Map<String, String> headers) {
        return webClientAlbums.getAlbums(albumId, headers);
    }
}
