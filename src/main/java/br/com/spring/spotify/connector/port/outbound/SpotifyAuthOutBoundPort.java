package br.com.spring.spotify.connector.port.outbound;

import br.com.spring.spotify.connector.domain.model.authentication.Token;

public interface SpotifyAuthOutBoundPort {
    Token getAppToken();
}
