package br.com.spring.spotify.connector.adapter.outbound.integration.webclient.resources;

import br.com.spring.spotify.connector.adapter.util.CustomLogApplication;
import br.com.spring.spotify.connector.adapter.util.StringUtils;
import br.com.spring.spotify.connector.domain.model.exceptions.statusCode.HandleStatusCodeException;
import br.com.spring.spotify.connector.domain.model.exceptions.custom.BadRequestException;
import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class WebClientAlbums {

    private final WebClient webClient;
    private final HandleStatusCodeException handleStatusCodeException;
    private final CustomLogApplication customLogApplication;

    public WebClientAlbums(
            WebClient.Builder webClientBuilder,
            HandleStatusCodeException handleStatusCodeException,
            CustomLogApplication customLogApplication
    ) {
        this.webClient = webClientBuilder
                .baseUrl(StringUtils.SPOTIFY_API_INBOUND_ADDRESS + StringUtils.SPOTIFY_API_VERSION_DESTINATION)
                .build();
        this.handleStatusCodeException = handleStatusCodeException;
        this.customLogApplication = customLogApplication;
    }

    public ResponseEntity<SpotifyAlbumDataOutput> getAlbums(String albumId, Map<String, String> headers) {
        String destination = StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESTINATION + "/" + albumId;
        logRequest(StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESCRIPTION, destination);

        return performGetRequest(destination, headers, SpotifyAlbumDataOutput.class);
    }

    public ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(List<String> albumsId, Map<String, String> headers) {
        if (albumsId.size() > 20) {
            throw new BadRequestException(
                    "The list of album IDs contains " + albumsId.size() + " items, but the maximum allowed is 20."
            );
        }

        String destination = StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESTINATION + "?ids=" + String.join(",", albumsId);
        logRequest(StringUtils.SPOTIFY_LIST_ALBUMS_RESOURCE_DESCRIPTION, destination);

        return performGetRequest(destination, headers, SpotifyListAlbumsDataOutput.class);
    }

    private <T> ResponseEntity<T> performGetRequest(String uri, Map<String, String> headers, Class<T> responseType) {
        return this.webClient
                .get()
                .uri(uri)
                .headers(httpHeaders -> httpHeaders.setAll(headers))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> handleStatusCodeException.handleResponseStatus(response.statusCode().value())
                )
                .toEntity(responseType)
                .block();
    }

    private void logRequest(String description, String resource) {
        customLogApplication.setLogInfoWebClient(description, resource);
    }
}

