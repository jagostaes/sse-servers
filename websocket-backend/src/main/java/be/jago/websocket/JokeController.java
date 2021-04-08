package be.jago.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class JokeController {

    private final JokeService jokeService;

    JokeController(JokeService jokeService){
        this.jokeService = jokeService;
    }

    @MessageMapping("/joke")
    @SendTo("/topic/jokes")
    public ChuckNorrisJoke getChuckNorrisJoke(){
        return jokeService.getRandomChuckNorrisJoke();
    }
}
