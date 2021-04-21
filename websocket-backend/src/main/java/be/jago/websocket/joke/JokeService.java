package be.jago.websocket.joke;

import org.springframework.stereotype.Service;

@Service
public interface JokeService {
    ChuckNorrisJoke getRandomChuckNorrisJoke();
}
