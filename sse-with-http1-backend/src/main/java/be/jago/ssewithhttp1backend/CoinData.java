package be.jago.ssewithhttp1backend;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CoinData {
    private ArrayList<Coin> coins = new ArrayList<>();

    public void setData(LinkedHashMap<String, LinkedHashMap<String, Number>> responseBody) {
        for (String coinName : responseBody.keySet()) {
            Coin coin = new Coin(coinName);
            LinkedHashMap<String, Number> priceData = responseBody.get(coinName);
            for (String currency : priceData.keySet()) {
                coin.getPriceData().put(currency, priceData.get(currency));
            }
            coins.add(coin);
        }
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }
}
