package br.com.spring.spotify.connector.domain.service;

import br.com.spring.spotify.connector.adapter.outbound.integration.webclient.authentication.WebClientRequestToken;
import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import br.com.spring.spotify.connector.domain.usecase.UseCaseSpotifyConnectorGetAlbumsById;
import br.com.spring.spotify.connector.domain.usecase.UseCaseSpotifyConnectorGetListAlbumsById;
import br.com.spring.spotify.connector.port.outbound.SpotifyAlbumsOutBoundPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class SpotifySevice implements UseCaseSpotifyConnectorGetAlbumsById, UseCaseSpotifyConnectorGetListAlbumsById {

    private final WebClientRequestToken webClientRequestToken;
    private final SpotifyAlbumsOutBoundPort spotifyAlbumsOutBoundPort;

    private Map<String, String> getAccessToken(){
        Map<String, String> headers = new HashMap<>();

        headers.remove("Authorization");
        headers.put("Authorization", "Bearer "+ webClientRequestToken.createToken().access_token());

        return headers;
    }

    @Override
    public ResponseEntity<SpotifyAlbumDataOutput> getAlbums(String albumId) {
        return spotifyAlbumsOutBoundPort.getAlbums(albumId, getAccessToken());
    }

    @Override
    public ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(List<String> albumsId) {
        return spotifyAlbumsOutBoundPort.getListAlbums(albumsId, getAccessToken());
    }
}
