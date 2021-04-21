package be.jago.ssehttp2.joke;

import org.springframework.stereotype.Service;

@Service
public interface JokeService {
    ChuckNorrisJoke getRandomChuckNorrisJoke();
}
