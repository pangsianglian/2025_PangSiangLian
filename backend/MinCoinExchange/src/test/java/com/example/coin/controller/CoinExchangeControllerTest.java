package com.example.coin.controller;

import com.example.coin.api.CoinRequest;
import com.example.coin.api.CoinResponse;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoinExchangeControllerTest {

    @Test
    public void testCoinExchange_Example1() {
        CoinExchangeController controller = new CoinExchangeController();

        CoinRequest request = new CoinRequest();
        request.setAmount(7.03);
        request.setDenominations(Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0));

        CoinResponse response = controller.exchange(request);

        List<Double> expected = Arrays.asList(0.01, 0.01, 0.01, 1.0, 1.0, 5.0);
        assertListsEqual(expected, response.getCoins());
    }

    @Test
    public void testCoinExchange_Example2() {
        CoinExchangeController controller = new CoinExchangeController();

        CoinRequest request = new CoinRequest();
        request.setAmount(103.0);
        request.setDenominations(Arrays.asList(1.0, 2.0, 50.0));

        CoinResponse response = controller.exchange(request);

        List<Double> expected = Arrays.asList(1.0, 2.0, 50.0, 50.0);
        assertListsEqual(expected, response.getCoins());
    }
    @Test
    public void testCoinExchangeExample3() {
        // Arrange
        CoinExchangeController controller = new CoinExchangeController();

        CoinRequest request = new CoinRequest();
        request.setAmount(6.75);
        request.setDenominations(Arrays.asList(10.0,5.0, 1.0, 0.5, 0.2,0.1,0.05,0.01));

        // Act
        CoinResponse response = controller.exchange(request);

        // Assert
        List<Double> expected = Arrays.asList(0.05,0.2,0.5,1.0,5.0);
        List<Double> actual = response.getCoins();

        assertEquals(expected.size(), actual.size(), "Coin list sizes mismatch");
    }

    @Test
    public void testCoinExchangeExample4() {
        // Arrange
        CoinExchangeController controller = new CoinExchangeController();

        CoinRequest request = new CoinRequest();
        request.setAmount(6.75);
        request.setDenominations(Arrays.asList(10.0,2.0, 1.0, 0.5, 0.2,0.1,0.05,0.01));

        // Act
        CoinResponse response = controller.exchange(request);

        // Assert
        List<Double> expected = Arrays.asList(0.05,0.2,0.5,2.0,2.0,2.0);
        List<Double> actual = response.getCoins();

        assertEquals(expected.size(), actual.size(), "Coin list sizes mismatch");
    }

    private void assertListsEqual(List<Double> expected, List<Double> actual) {
        assertEquals(expected.size(), actual.size(), "Coin list sizes mismatch");

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), 0.0001,
                    "Mismatch at index " + i + ": expected " + expected.get(i) + ", got " + actual.get(i));
        }
    }

    @Test
    public void testEmptyDenominations() {
        CoinExchangeController controller = new CoinExchangeController();

        CoinRequest request = new CoinRequest();
        request.setAmount(5.0);
        request.setDenominations(List.of());

        Exception exception = assertThrows(
                javax.ws.rs.WebApplicationException.class,
                () -> controller.exchange(request)
        );

        assertEquals("Cannot make exact change.", exception.getMessage());
    }

    @Test
    public void testImpossibleAmount() {
        CoinExchangeController controller = new CoinExchangeController();

        CoinRequest request = new CoinRequest();
        request.setAmount(0.03);
        request.setDenominations(List.of(0.05));

        Exception exception = assertThrows(
                javax.ws.rs.WebApplicationException.class,
                () -> controller.exchange(request)
        );

        assertEquals("Cannot make exact change.", exception.getMessage());
    }

    @Test
    public void testNegativeAmount() {
        CoinExchangeController controller = new CoinExchangeController();

        CoinRequest request = new CoinRequest();
        request.setAmount(-5.0);
        request.setDenominations(List.of(1.0, 5.0));

        Exception exception = assertThrows(
                javax.ws.rs.WebApplicationException.class,
                () -> controller.exchange(request)
        );

        assertEquals("Amount must be greater than 0.", exception.getMessage());
    }

    @Test
    public void testAmountExceedsUpperLimit() {
        CoinExchangeController controller = new CoinExchangeController();

        CoinRequest request = new CoinRequest();
        request.setAmount(15000.00);
        request.setDenominations(Arrays.asList(5.0, 10.0));

        WebApplicationException ex = assertThrows(
                WebApplicationException.class,
                () -> controller.exchange(request)
        );

        assertEquals("Amount must not exceed 10,000.00.", ex.getMessage());
    }
}
