package com.example.coin.controller;

import com.example.coin.api.CoinRequest;
import com.example.coin.api.CoinResponse;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

@Path("/coin-exchange")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CoinExchangeController {
    @POST
    public CoinResponse exchange(CoinRequest req) {
        double target = req.getAmount();
        // Target amount must be within the range between 0 and 10,000.00
        if (target <= 0) {
            throw new WebApplicationException("Amount must be greater than 0.", 400);
        }
        if (target > 10_000.00) {
            throw new WebApplicationException("Amount must not exceed 10,000.00.", 400);
        }
        List<Double> denominations = req.getDenominations();

        int targetCents = (int) Math.round(target * 100);
        List<Integer> denomCents = new ArrayList<>();
        for (Double d : denominations) {
            denomCents.add((int) Math.round(d * 100));
        }

        int[] dp = new int[targetCents + 1];
        int[] lastCoin = new int[targetCents + 1];

        // You can assume that you have infinite number of coins for each denomination
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 1; i <= targetCents; i++) {
            for (int coin : denomCents) {
                if (i >= coin && dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    lastCoin[i] = coin;
                }
            }
        }

        if (dp[targetCents] == Integer.MAX_VALUE - 1) {
            throw new WebApplicationException("Cannot make exact change.", 400);
        }

        List<Double> result = new ArrayList<>();
        int curr = targetCents;
        while (curr > 0) {
            int coin = lastCoin[curr];
            result.add(coin / 100.0);
            curr -= coin;
        }
        // A list of minimum number of coins in ascending order
        Collections.sort(result);
        return new CoinResponse(result);
    }
}
