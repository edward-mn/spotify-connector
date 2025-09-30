package br.com.spring.spotify.connector.adapter.inbound.rest;

import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import br.com.spring.spotify.connector.domain.usecase.GetAlbumByIdUseCase;
import br.com.spring.spotify.connector.domain.usecase.GetAlbumsListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/spotify-connector/albums/v1")
public class AlbumController {

    private final GetAlbumByIdUseCase getAlbumByIdUseCase;
    private final GetAlbumsListUseCase getAlbumsListUseCase;

    @GetMapping("/{albumId}")
    public ResponseEntity<SpotifyAlbumDataOutput> getAlbums(
            @PathVariable String albumId
    ){
        return getAlbumByIdUseCase.getAlbums(albumId);
    }

    @GetMapping("/list")
    public ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(
            @RequestParam List<String> albumsId
    ){
        return getAlbumsListUseCase.getListAlbums(albumsId);
    }
}
