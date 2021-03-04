package be.jago.ssewithhttp1backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;

@RestController
@RequestMapping("sse-server")
public class ApiController {

    @Autowired
    private CoinService coinService;

    private final static String[] coins = {"bitcoin", "chainlink", "ethereum"};
    private final static String[] currencies = {"usd", "eur", "gbp"};

    @GetMapping("/coin-price-stream")
    public Flux<ServerSentEvent<CoinData>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(sequence -> ServerSentEvent.<CoinData>builder()
                        .event("coin-price-update")
                        .data(coinService.getCoinData(Arrays.asList(coins), Arrays.asList(currencies)))
                        .build());
    }

    @GetMapping("/coins")
    public CoinData getCoinData() {
        return coinService.getCoinData(Arrays.asList(coins), Arrays.asList(currencies));
    }
}
