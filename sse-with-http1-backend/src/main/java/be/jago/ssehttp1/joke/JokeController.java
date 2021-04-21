package be.jago.ssehttp1.joke;

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

    JokeController(JokeService jokeService){
        this.jokeService = jokeService;
    }

    @GetMapping("/chuck-norris-joke-stream")
    public Flux<ServerSentEvent<ChuckNorrisJoke>> streamJokes() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(sequence -> ServerSentEvent.<ChuckNorrisJoke>builder()
                .data(jokeService.getRandomChuckNorrisJoke())
                .build());
    }
}
