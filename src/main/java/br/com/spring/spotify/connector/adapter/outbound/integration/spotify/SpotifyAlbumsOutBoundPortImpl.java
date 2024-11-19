package br.com.spring.spotify.connector.adapter.outbound.integration.spotify;

import br.com.spring.spotify.connector.adapter.outbound.integration.webclient.resources.WebClientAlbums;
import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import br.com.spring.spotify.connector.port.outbound.SpotifyAlbumsOutBoundPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class SpotifyAlbumsOutBoundPortImpl implements SpotifyAlbumsOutBoundPort {

    private final WebClientAlbums webClientAlbums;

    @Override
    public ResponseEntity<SpotifyAlbumDataOutput> getAlbums(String albumId, Map<String, String> headers) {
        return webClientAlbums.getAlbums(albumId, headers);
    }

    @Override
    public ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(List<String> albumsId, Map<String, String> headers) {
        return webClientAlbums.getListAlbums(albumsId, headers);
    }
}
