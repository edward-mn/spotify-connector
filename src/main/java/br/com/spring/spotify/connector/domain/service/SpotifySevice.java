package br.com.spring.spotify.connector.domain.service;

import br.com.spring.spotify.connector.adapter.outbound.integration.webclient.authentication.WebClientRequestToken;
import br.com.spring.spotify.connector.domain.usecase.UseCaseSpotifyConnectorGetAlbumsById;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

@Service
public class SpotifySevice {
    private final WebClientRequestToken webClientRequestToken;
    private final UseCaseSpotifyConnectorGetAlbumsById useCaseSpotifyConnectorGetAlbumsById;

    public SpotifySevice(
            WebClientRequestToken webClientRequestToken,
            UseCaseSpotifyConnectorGetAlbumsById useCaseSpotifyConnectorGetAlbumsById
    ) {
        this.webClientRequestToken = webClientRequestToken;
        this.useCaseSpotifyConnectorGetAlbumsById = useCaseSpotifyConnectorGetAlbumsById;
    }

    public ResponseEntity<Object> getAlbumsById(String albumId){
        return useCaseSpotifyConnectorGetAlbumsById.getAlbums(albumId, getAccessToken());
    }

    private Map<String, String> getAccessToken(){
        Map<String, String> headers = new HashMap<>();

        headers.remove("Authorization");
        headers.put("Authorization", "Bearer "+ webClientRequestToken.createToken().access_token());

        return headers;
    }
}
