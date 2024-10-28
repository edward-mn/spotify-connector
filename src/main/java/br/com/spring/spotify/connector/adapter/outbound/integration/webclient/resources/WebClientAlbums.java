package br.com.spring.spotify.connector.adapter.outbound.integration.webclient.resources;

import br.com.spring.spotify.connector.adapter.util.CustomLogApplication;
import br.com.spring.spotify.connector.adapter.util.StringUtils;
import br.com.spring.spotify.connector.domain.model.exceptions.HandleStatusCodeException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class WebClientAlbums {

    private final WebClient.Builder webClient;
    private final HandleStatusCodeException handleStatusCodeException;

    public WebClientAlbums(
            WebClient.Builder webClient,
            HandleStatusCodeException handleStatusCodeException,
            CustomLogApplication customLogApplication
    ) {
        this.webClient = webClient;
        this.handleStatusCodeException = handleStatusCodeException;
        this.customLogApplication = customLogApplication;
    }

    private final CustomLogApplication customLogApplication;

    public ResponseEntity<Object> getAlbums(String albumId, Map<String, String> headers){
        String destination = buildHost() + StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESTINATION;
        customLogApplication.setLogInfoWebClient(
                StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESCRIPTION, StringUtils.SPOTIFY_ALBUMS_RESOURCE_DESTINATION
        );

        return this.webClient.build()
                .get()
                .uri(destination + albumId)
                .headers(httpHeaders -> httpHeaders.setAll(headers))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> handleStatusCodeException.handleResponseStatus(response.statusCode().value())
                )
                .toEntity(Object.class)
                .block();
    }

    private String buildHost(){
        return StringUtils.SPOTIFY_API_INBOUND_ADDRESS + StringUtils.SPOTIFY_API_VERSION_DESTINATION;
    }
}
