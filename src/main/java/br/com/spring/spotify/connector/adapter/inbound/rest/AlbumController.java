package br.com.spring.spotify.connector.adapter.inbound.rest;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.usecase.UseCaseSpotifyConnectorGetAlbumsById;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spotify-connector/albums/v1")
public class AlbumController {

    private final UseCaseSpotifyConnectorGetAlbumsById useCaseSpotifyConnectorGetAlbumsById;

    @GetMapping("/{albumId}")
    public ResponseEntity<SpotifyAlbumDataOutput> getAlbums(
            @PathVariable String albumId
    ){
        return useCaseSpotifyConnectorGetAlbumsById.getAlbums(albumId);
    }
}
