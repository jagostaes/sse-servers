package be.jago.ssehttp2.joke;

import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("sse-server")
public class JokeController {

    private final JokeService jokeService;
    private final Duration interval = Duration.ofSeconds(5);

    JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping(value = "/chuck-norris-joke-stream")
    public Flux<ServerSentEvent<ChuckNorrisJoke>> streamEvents() {
        return Flux.interval(interval)
                .map(sequence -> ServerSentEvent.<ChuckNorrisJoke>builder()
                        .data(jokeService.getRandomChuckNorrisJoke())
                        .build());
    }
}
