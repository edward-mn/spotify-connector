package br.com.spring.spotify.connector.adapter.outbound.integration.webclient.authentication;

import br.com.spring.spotify.connector.adapter.util.CustomLogApplication;
import br.com.spring.spotify.connector.adapter.util.StringUtils;
import br.com.spring.spotify.connector.config.authentication.AuthenticationConfig;
import br.com.spring.spotify.connector.domain.model.authentication.Token;
import br.com.spring.spotify.connector.domain.model.exceptions.HandleStatusCodeException;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientRequestToken {

    private final AuthenticationConfig authenticationConfig;
    private final CustomLogApplication customLogApplication;
    private final HandleStatusCodeException handleStatusCodeException;
    private final WebClient.Builder webClient;

    public WebClientRequestToken(
            AuthenticationConfig authenticationConfig,
            CustomLogApplication customLogApplication,
            HandleStatusCodeException handleStatusCodeException,
            WebClient.Builder webClient
    ) {
        this.authenticationConfig = authenticationConfig;
        this.customLogApplication = customLogApplication;
        this.handleStatusCodeException = handleStatusCodeException;
        this.webClient = webClient;
    }

    public Token createToken() {
        String authenticationUri = StringUtils.SPOTIFY_AUTHENTICATION_INBOUND_ADDRESS + StringUtils.SPOTIFY_AUTHENTICATION_RESOURCE_DESTINATION;

        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();

        bodyMap.add("grant_type", authenticationConfig.getGrantType());
        bodyMap.add("client_id", authenticationConfig.getClientId());
        bodyMap.add("client_secret", authenticationConfig.getClientSecret());

        customLogApplication.setLogInfoWebClient(StringUtils.SPOTIFY_AUTHENTICATION_RESOURCE_DESCRIPTION, StringUtils.SPOTIFY_AUTHENTICATION_RESOURCE_DESTINATION);

        return this.webClient.build()
                .post()
                .uri(authenticationUri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(BodyInserters.fromValue(bodyMap))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> handleStatusCodeException.handleResponseStatus(response.statusCode().value())
                )
                .bodyToMono(Token.class)
                .block();
    }
}
