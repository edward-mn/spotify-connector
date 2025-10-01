package br.com.spring.spotify.connector.config;

import br.com.spring.spotify.connector.config.authentication.SpotifyAuthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties({ SpotifyAuthProperties.class })
public class SpotifyConfig {
    @Bean("spotifyAuthClient")
    public WebClient spotifyAuthClient(WebClient.Builder builder, SpotifyAuthProperties props) {
        return builder.baseUrl(props.getBaseUrl()).build();
    }
}
