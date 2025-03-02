package be.jago.ssehttp1.joke;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class JokeServiceImpl implements JokeService {

    private final RestClient restClient;
    private final HttpHeaders httpHeaders;

    JokeServiceImpl(RestClient restClient,
                    HttpHeaders httpHeaders) {
        this.restClient = restClient;
        this.httpHeaders = httpHeaders;
    }

    @Override
    public ChuckNorrisJoke getRandomChuckNorrisJoke() {
        return restClient.get()
                .uri("https://api.chucknorris.io/jokes/random")
                .headers(headers -> headers.addAll(httpHeaders))
                .retrieve()
                .body(ChuckNorrisJoke.class);
    }
}
