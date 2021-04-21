package be.jago.ssehttp1.joke;

import org.springframework.stereotype.Service;

@Service
public interface JokeService {
    ChuckNorrisJoke getRandomChuckNorrisJoke();
}
