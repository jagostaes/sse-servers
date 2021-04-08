package be.jago.websocket;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class JokeServiceImpl implements JokeService{

    private final HttpHeaders httpHeaders;
    private final RestTemplate restTemplate;

    private final String chuckNorrisJokeUrl = "https://api.chucknorris.io/jokes/random";


    JokeServiceImpl(HttpHeaders httpHeaders, RestTemplate restTemplate){
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
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
