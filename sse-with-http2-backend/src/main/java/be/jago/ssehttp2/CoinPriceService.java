package be.jago.ssehttp2;

import be.jago.ssehttp2.coins.CryptoCurrency;
import org.springframework.stereotype.Service;

@Service
public interface CoinPriceService {
    CryptoCurrency updateCrypto();
}
