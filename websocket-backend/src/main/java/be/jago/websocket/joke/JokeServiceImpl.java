package be.jago.websocket.joke;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class JokeServiceImpl implements JokeService, InitializingBean {

    private final HttpHeaders httpHeaders;
    private final RestClient restClient;
    private final SimpMessagingTemplate simpMessagingTemplate;

    JokeServiceImpl(HttpHeaders httpHeaders,
                    RestClient restClient,
                    SimpMessagingTemplate simpMessagingTemplate) {
        this.httpHeaders = httpHeaders;
        this.restClient = restClient;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public ChuckNorrisJoke getRandomChuckNorrisJoke() {
        return restClient.get()
                .uri("https://api.chucknorris.io/jokes/random")
                .headers(headers -> headers.addAll(httpHeaders))
                .retrieve()
                .body(ChuckNorrisJoke.class);
    }

    @Override
    public void afterPropertiesSet() {
        Flux.interval(Duration.ofSeconds(5L))
                .map((n) -> getRandomChuckNorrisJoke())
                .subscribe(message -> simpMessagingTemplate.convertAndSend("/joke/joke-stream", message));
    }
}
