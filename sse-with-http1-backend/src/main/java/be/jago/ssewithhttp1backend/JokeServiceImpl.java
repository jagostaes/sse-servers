package be.jago.ssewithhttp1backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JokeServiceImpl  implements JokeService{

    private RestTemplate restTemplate;

    private HttpHeaders httpHeaders;

    private final String chuckNorrisJokeUrl = "https://api.chucknorris.io/jokes/random";

    JokeServiceImpl(RestTemplate restTemplate, HttpHeaders httpHeaders){
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    @Override
    public ChuckNorrisJoke getRandomChuckNorrisJoke() {
        ChuckNorrisJoke joke = new ChuckNorrisJoke();
        ResponseEntity<ChuckNorrisJoke> response = restTemplate.exchange(chuckNorrisJokeUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), ChuckNorrisJoke.class);
        if (response.hasBody()) {
            joke.setValue(response.getBody().getValue());
        }
        return joke;
    }
}
