package be.jago.ssehttp2.joke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebFluxTest(JokeController.class)
@WithMockUser
class JokeControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JokeController jokeController;

    @MockBean
    private JokeServiceImpl jokeService;

    @BeforeEach
    void setUp() throws Exception {
        Field intervalField = JokeController.class.getDeclaredField("interval");
        intervalField.setAccessible(true);
        intervalField.set(jokeController, Duration.ofMillis(100));
    }

    @Test
    void streamEvents_shouldReturnJokesAsSSE() {
        ChuckNorrisJoke mockJoke = new ChuckNorrisJoke("Chuck Norris can divide by zero.");
        when(jokeService.getRandomChuckNorrisJoke()).thenReturn(mockJoke);

        Objects.requireNonNull(webTestClient.get()
                        .uri("/sse-server/chuck-norris-joke-stream")
                        .accept(MediaType.TEXT_EVENT_STREAM)
                        .exchange()
                        .expectStatus().isOk()
                        .returnResult(ChuckNorrisJoke.class)
                        .getResponseBody()
                        .take(3)
                        .collectList()
                        .block(Duration.ofSeconds(3)))
                .forEach(joke -> {
                    assertThat(joke).isNotNull();
                    assertThat(joke.value()).isEqualTo(mockJoke.value());
                });

        Mockito.verify(jokeService, Mockito.atLeastOnce()).getRandomChuckNorrisJoke();
    }
}
