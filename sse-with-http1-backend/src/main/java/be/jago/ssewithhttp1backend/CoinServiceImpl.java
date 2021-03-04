package be.jago.ssewithhttp1backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CoinServiceImpl implements CoinService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    private final String url = "https://api.coingecko.com/api/v3/simple/price";

    @Override
    public CoinData getCoinData(List<String> coins, List<String> currencies) {
        CoinData coinData = new CoinData();
        String uri = getCoinPriceUriString(url, coins, currencies);

        ResponseEntity<Map> response = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(httpHeaders), Map.class);
        if (response.hasBody()) {
            coinData.setData((LinkedHashMap<String, LinkedHashMap<String, Number>>) response.getBody());
        }
        return coinData;
    }

    private String getCoinPriceUriString(String url, List<String> coins, List<String> currencies) {
        StringBuilder uri = new StringBuilder(url);
        uri.append("?ids=");
        coins.forEach(coin -> uri.append(coin).append(","));
        uri.deleteCharAt(uri.length() - 1);
        uri.append("&vs_currencies=");
        currencies.forEach(currency -> uri.append(currency).append(","));
        uri.deleteCharAt(uri.length() - 1);

        return uri.toString();
    }
}
