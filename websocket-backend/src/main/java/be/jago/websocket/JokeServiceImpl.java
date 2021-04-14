package be.jago.websocket;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Objects;

@Service
public class JokeServiceImpl implements JokeService, InitializingBean {

    private final HttpHeaders httpHeaders;
    private final RestTemplate restTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final String chuckNorrisJokeUrl = "https://api.chucknorris.io/jokes/random";

    JokeServiceImpl(HttpHeaders httpHeaders, RestTemplate restTemplate, SimpMessagingTemplate simpMessagingTemplate){
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
        this.simpMessagingTemplate = simpMessagingTemplate;
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

    @Override
    public void afterPropertiesSet(){
        Flux.interval(Duration.ofSeconds(5L))
                .map((n) -> getRandomChuckNorrisJoke())
                .subscribe(message -> simpMessagingTemplate.convertAndSend("/joke/jokestream", message));
    }
}
