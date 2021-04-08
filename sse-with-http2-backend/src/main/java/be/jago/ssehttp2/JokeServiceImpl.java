package be.jago.ssehttp2;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class JokeServiceImpl  implements JokeService{

    private final RestTemplate restTemplate;

    private final HttpHeaders httpHeaders;

    private final String chuckNorrisJokeUrl = "https://api.chucknorris.io/jokes/random";

    JokeServiceImpl(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    @Override
    public ChuckNorrisJoke getRandomChuckNorrisJoke() {
        ChuckNorrisJoke joke = new ChuckNorrisJoke();
        ResponseEntity<ChuckNorrisJoke> response = restTemplate.exchange(chuckNorrisJokeUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), ChuckNorrisJoke.class);
        if (response.hasBody()) {
            joke.setValue(Objects.requireNonNull(response.getBody()).getValue());
        }
        return joke;
    }
}
