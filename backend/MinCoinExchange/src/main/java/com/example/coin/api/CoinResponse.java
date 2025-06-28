package com.example.coin.api;

import java.util.List;

public class CoinResponse {
    private List<Double> coins;

    public CoinResponse(List<Double> coins) {
        this.coins = coins;
    }

    public List<Double> getCoins() {
        return coins;
    }
}
