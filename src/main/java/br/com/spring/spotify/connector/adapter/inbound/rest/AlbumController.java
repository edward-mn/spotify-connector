package br.com.spring.spotify.connector.adapter.inbound.rest;

import br.com.spring.spotify.connector.domain.service.SpotifySevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spotify-connector/albums/v1")
public class AlbumController {

    private final SpotifySevice spotifySevice;

    public AlbumController(SpotifySevice spotifySevice) {
        this.spotifySevice = spotifySevice;
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Object> getAlbums(
            @PathVariable String albumId
    ){
        return spotifySevice.getAlbumsById(albumId);
    }
}
