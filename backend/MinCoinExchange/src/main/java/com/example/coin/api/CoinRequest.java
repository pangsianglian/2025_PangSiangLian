package com.example.coin.api;

// import jakarta.validation.constraints.*;
import java.util.List;
import javax.validation.constraints.*;

public class CoinRequest {

    @NotNull(message = "Amount cannot be null.")
    @DecimalMin(value = "0.00", inclusive = false, message = "Amount must be greater than 0.")
    @DecimalMax(value = "10000.00", inclusive = true, message = "Amount must not exceed 10,000.00.")
    @Digits(integer = 6, fraction = 2, message = "Amount can have up to 2 decimal places.")
    private Double amount;

    @NotEmpty(message = "Denominations list cannot be empty.")
    private List<
            @NotNull(message = "Denomination cannot be null.")
            @DecimalMin(value = "0.01", inclusive = true, message = "Denomination must be at least 0.01.")
            @Digits(integer = 6, fraction = 2, message = "Denomination can have up to 2 decimal places.")
                    Double
            > denominations;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Double> getDenominations() {
        return denominations;
    }

    public void setDenominations(List<Double> denominations) {
        this.denominations = denominations;
    }
}
