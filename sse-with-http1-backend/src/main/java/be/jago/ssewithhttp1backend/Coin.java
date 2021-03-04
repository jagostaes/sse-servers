package be.jago.ssewithhttp1backend;

import java.util.HashMap;
import java.util.Map;

public class Coin {
    private String name;
    private Map<String, Number> priceData = new HashMap<>();

    public Coin(String name) {
        this.name = name;
    }

    public Map<String, Number> getPriceData() {
        return priceData;
    }

    public String getName() {
        return name;
    }
}
