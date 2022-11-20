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
        this.amount = amount;
        this.currency = currency;
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
