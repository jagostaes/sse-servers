package be.jago.ssehttp2;

import be.jago.ssehttp2.coins.CryptoCurrency;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("sse-server")
public class CoinPriceController {

    CoinPriceService coinPriceService;

    @GetMapping("/price-stream-sse")
    public Flux<ServerSentEvent<CryptoCurrency>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<CryptoCurrency> builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data(coinPriceService.updateCrypto())
                        .build());
    }
}
