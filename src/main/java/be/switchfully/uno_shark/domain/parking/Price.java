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

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Double.compare(price.amount, amount) == 0 && Objects.equals(currency, price.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
