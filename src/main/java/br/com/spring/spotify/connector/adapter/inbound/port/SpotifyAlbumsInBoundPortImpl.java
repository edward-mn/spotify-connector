package br.com.spring.spotify.connector.adapter.inbound.port;

import br.com.spring.spotify.connector.port.inbound.SpotifyAlbumsInBoundPort;
import br.com.spring.spotify.connector.port.outbound.SpotifyAlbumsOutBoundPort;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class SpotifyAlbumsInBoundPortImpl implements SpotifyAlbumsInBoundPort {

    private final SpotifyAlbumsOutBoundPort spotifyAlbumsOutBoundPort;

    public SpotifyAlbumsInBoundPortImpl(SpotifyAlbumsOutBoundPort spotifyAlbumsOutBoundPort) {
        this.spotifyAlbumsOutBoundPort = spotifyAlbumsOutBoundPort;
    }

    @Override
    public ResponseEntity<Object> getAlbums(String albumId, Map<String, String> headers) {
        return spotifyAlbumsOutBoundPort.getAlbums(albumId, headers);
    }
}
