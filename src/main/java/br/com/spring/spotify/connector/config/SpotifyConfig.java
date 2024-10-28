package br.com.spring.spotify.connector.config;

import br.com.spring.spotify.connector.adapter.inbound.port.SpotifyAlbumsInBoundPortImpl;
import br.com.spring.spotify.connector.adapter.outbound.integration.spotify.SpotifyAlbumsOutBoundPortImpl;
import br.com.spring.spotify.connector.adapter.outbound.integration.webclient.authentication.WebClientRequestToken;
import br.com.spring.spotify.connector.domain.service.SpotifySevice;
import br.com.spring.spotify.connector.domain.usecase.UseCaseSpotifyConnectorGetAlbumsById;
import br.com.spring.spotify.connector.port.inbound.SpotifyAlbumsInBoundPort;
import br.com.spring.spotify.connector.port.outbound.SpotifyAlbumsOutBoundPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyConfig {

    @Bean("spotifyAlbumsInBoundPort")
    public SpotifyAlbumsInBoundPort spotifyAlbumsInBoundPort(
            SpotifyAlbumsOutBoundPort spotifyAlbumsOutBoundPort
    ){
        return new SpotifyAlbumsInBoundPortImpl(spotifyAlbumsOutBoundPort);
    }

    @Bean("spotifyAlbumsOutBoundPort")
    public SpotifyAlbumsOutBoundPort spotifyAlbumsOutBoundPort(){
        return new SpotifyAlbumsOutBoundPortImpl();
    }

    @Bean("useCaseSpotifyConnectorGetAlbumsById")
    public UseCaseSpotifyConnectorGetAlbumsById useCaseSpotifyConnectorGetAlbumsById(
            SpotifyAlbumsInBoundPort spotifyAlbumsInBoundPort
    ){
        return new UseCaseSpotifyConnectorGetAlbumsById(spotifyAlbumsInBoundPort);
    }

    @Bean("spotifySevice")
    public SpotifySevice spotifySevice(
            WebClientRequestToken webClientRequestToken,
            UseCaseSpotifyConnectorGetAlbumsById useCaseSpotifyConnectorGetAlbumsById
    ){
        return new SpotifySevice(
                webClientRequestToken,
                useCaseSpotifyConnectorGetAlbumsById
        );
    }
}
