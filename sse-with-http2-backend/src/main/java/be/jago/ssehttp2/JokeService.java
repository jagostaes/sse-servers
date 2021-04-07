package be.jago.ssehttp2;

import org.springframework.stereotype.Service;

@Service
public interface JokeService {
    ChuckNorrisJoke getRandomChuckNorrisJoke();
}
