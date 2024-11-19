package br.com.spring.spotify.connector.adapter.inbound.rest;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import br.com.spring.spotify.connector.domain.usecase.UseCaseSpotifyConnectorGetAlbumsById;
import br.com.spring.spotify.connector.domain.usecase.UseCaseSpotifyConnectorGetListAlbumsById;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spotify-connector/albums/v1")
public class AlbumController {

    private final UseCaseSpotifyConnectorGetAlbumsById useCaseSpotifyConnectorGetAlbumsById;
    private final UseCaseSpotifyConnectorGetListAlbumsById useCaseSpotifyConnectorGetListAlbumsById;

    @GetMapping("/{albumId}")
    public ResponseEntity<SpotifyAlbumDataOutput> getAlbums(
            @PathVariable String albumId
    ){
        return useCaseSpotifyConnectorGetAlbumsById.getAlbums(albumId);
    }

    @GetMapping("/list")
    public ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(
            @RequestParam List<String> albumsId
    ){
        return useCaseSpotifyConnectorGetListAlbumsById.getListAlbums(albumsId);
    }
}
