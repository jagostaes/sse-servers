package be.jago.ssewithhttp1backend;

import org.springframework.stereotype.Service;

@Service
public interface JokeService {
    ChuckNorrisJoke getRandomChuckNorrisJoke();
}
