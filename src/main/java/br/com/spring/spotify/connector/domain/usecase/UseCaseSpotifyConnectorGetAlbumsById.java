package br.com.spring.spotify.connector.domain.usecase;

import br.com.spring.spotify.connector.port.inbound.SpotifyAlbumsInBoundPort;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class UseCaseSpotifyConnectorGetAlbumsById {

    private final SpotifyAlbumsInBoundPort spotifyAlbumsInBoundPort;

    public UseCaseSpotifyConnectorGetAlbumsById(SpotifyAlbumsInBoundPort spotifyAlbumsInBoundPort) {
        this.spotifyAlbumsInBoundPort = spotifyAlbumsInBoundPort;
    }

    public ResponseEntity<Object> getAlbums(String albumId, Map<String, String> headers){
        return spotifyAlbumsInBoundPort.getAlbums(albumId, headers);
    }
}