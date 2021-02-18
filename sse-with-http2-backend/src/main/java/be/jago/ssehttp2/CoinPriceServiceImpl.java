package be.jago.ssehttp2;

import be.jago.ssehttp2.coins.Bitcoin;
import be.jago.ssehttp2.coins.CryptoCurrency;
import org.springframework.stereotype.Service;

@Service
public class CoinPriceServiceImpl implements CoinPriceService{
    @Override
    public CryptoCurrency updateCrypto() {
        return new Bitcoin();
    }
}
