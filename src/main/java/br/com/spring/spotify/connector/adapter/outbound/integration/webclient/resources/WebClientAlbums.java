package br.com.spring.spotify.connector.adapter.outbound.integration.webclient.resources;

import br.com.spring.spotify.connector.adapter.util.CustomLogApplication;
import br.com.spring.spotify.connector.adapter.util.StringUtils;
import br.com.spring.spotify.connector.domain.model.exceptions.statusCode.HandleStatusCodeException;
import br.com.spring.spotify.connector.domain.model.exceptions.custom.BadRequestException;
import br.com.spring.spotify.connector.domain.service.authentication.SpotifyTokenProvider;
import br.com.spring.spotify.connector.domain.service.output.albums.SpotifyAlbumDataOutput;
import br.com.spring.spotify.connector.domain.service.output.listAlbums.SpotifyListAlbumsDataOutput;
import org.springframework.http.HttpHeaders;
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
    private final SpotifyTokenProvider tokenProvider;

    public WebClientAlbums(
            WebClient.Builder webClientBuilder,
            HandleStatusCodeException handleStatusCodeException,
            CustomLogApplication customLogApplication,
            SpotifyTokenProvider tokenProvider
    ) {
        this.webClient = webClientBuilder
                .baseUrl(StringUtils.SPOTIFY_API_INBOUND_ADDRESS + StringUtils.SPOTIFY_API_VERSION_DESTINATION)
                .build();
        this.handleStatusCodeException = handleStatusCodeException;
        this.customLogApplication = customLogApplication;
        this.tokenProvider = tokenProvider;
    }

    public ResponseEntity<SpotifyAlbumDataOutput> getAlbums(String albumId) {
        String destination = StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESTINATION + "/" + albumId;
        logRequest(StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESCRIPTION, destination);

        return performGetRequest(destination, SpotifyAlbumDataOutput.class);
    }

    public ResponseEntity<SpotifyListAlbumsDataOutput> getListAlbums(List<String> albumsId) {
        if (albumsId.size() > 20) {
            throw new BadRequestException(
                    "The list of album IDs contains " + albumsId.size() + " items, but the maximum allowed is 20."
            );
        }

        String destination = StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESTINATION + "?ids=" + String.join(",", albumsId);
        logRequest(StringUtils.SPOTIFY_LIST_ALBUMS_RESOURCE_DESCRIPTION, destination);

        return performGetRequest(destination, SpotifyListAlbumsDataOutput.class);
    }

    private <T> ResponseEntity<T> performGetRequest(String uri, Class<T> responseType) {
        String authHeader = tokenProvider.getAuthorizationHeader(); // "Bearer <token>"

        return this.webClient
                .get()
                .uri(uri)
                .header(HttpHeaders.AUTHORIZATION, authHeader)
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

