package io.mhan.spotifytest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@Service
public class SpotifyService {
    private final static String CLIENT_ID = "";
    private final static String CLIENT_SECRET = "";

    // 액세스 토큰 1시간
    // https://api.spotify.com/v1/search
    public SpotifyAccessTokenResponse getAccessToken() {
        SpotifyAccessTokenResponse response = WebClient.create()
                .post()
                .uri("https://accounts.spotify.com/api/token")
                .header(CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters
                        .fromFormData("grant_type", "client_credentials")
                        .with("client_id", CLIENT_ID)
                        .with("client_secret", CLIENT_SECRET)
                )
                .retrieve()
                .bodyToMono(SpotifyAccessTokenResponse.class)
                .block();

        return response;
    }

    public Map<String, Object> search(String accessToken, String query) {

        var response = WebClient.create()
                .get()
                .uri("https://api.spotify.com/v1/search?q={q}&market={market}&type={type}",
                        query, "KR", "album")
                .header(AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return response;
    }
}
