package io.mhan.spotifytest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final SpotifyService spotifyService;

    @GetMapping("/test")
    public SpotifyAccessTokenResponse test() {
        return spotifyService.getAccessToken();
    }

    @GetMapping("/search")
    public Map<String, Object> test(@RequestParam String accessToken, @RequestParam String query) {
        Map<String, Object> search = spotifyService.search(accessToken, query);

        return search;
    }
}
