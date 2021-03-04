package be.jago.ssewithhttp1backend;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CoinService {
    CoinData getCoinData(List<String> coins, List<String> currencies);
}
