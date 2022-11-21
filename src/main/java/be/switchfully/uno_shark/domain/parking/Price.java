package be.switchfully.uno_shark.domain.parking;

import javax.persistence.Embeddable;
import java.util.Currency;
import java.util.Objects;

@Embeddable
public class Price {
    private double amount;
    private Currency currency;

    public Price() {
    }

    public Price(double amount, Currency currency) {
        this.amount = amountVerification(amount);
        this.currency = currencyVerification(currency);
    }

    private double amountVerification(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Provide a valid price.");
        }
        return amount;
    }

    private Currency currencyVerification(Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("Provide a currency.");
        }
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
