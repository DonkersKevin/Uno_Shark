package be.switchfully.uno_shark.domain.parking;

import org.junit.jupiter.api.Test;

import java.util.Currency;

import static org.assertj.core.api.Assertions.*;

class PriceTest {

    @Test
    void givenValidPrice_noExceptionsThrown() {
        Price price = new Price(10.0, Currency.getInstance("EUR"));
        assertThat(price).isNotNull();
    }

    @Test
    void givenNegativeAmount_ThrowException() {
        assertThatThrownBy(() -> new Price(-10, Currency.getInstance("EUR")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid price.");
    }

    @Test
    void givenNoCurrency_ThrowException() {
        assertThatThrownBy(() -> new Price(10, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a currency.");
    }

    @Test
    void givenWrongCurrency_ThrowException() {
        assertThatThrownBy(() -> new Price(10, Currency.getInstance("EURIU")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}