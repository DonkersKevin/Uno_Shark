package be.switchfully.uno_shark.domain.person.address;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostalCodeTest {

    @Test
    void givenValidPostalCode_NoExceptionsThrown() {
        PostalCode postalCode = new PostalCode("1000", "BXL");
        assertThat(postalCode).isNotNull();
    }

    @Test
    void givenNoPostalCode_getException() {
        assertThatThrownBy(() -> new PostalCode(null, "BXL"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Postal Code cannot be empty.");
    }

    @Test
    void givenTooLongPostalCode_getException() {
        assertThatThrownBy(() -> new PostalCode("99999", "BXL"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a valid postal code: format ####");
    }

    @Test
    void givenNoCityName_getException() {
        assertThatThrownBy(() -> new PostalCode("9999", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a city name.");
    }

    @Test
    void givenTwoCitiesSameInfo_AreEqual() {
        PostalCode postalCode1 = new PostalCode("1000", "Brussel");
        PostalCode postalCode2 = new PostalCode("1000", "Brussel");
        assertThat(postalCode1).isEqualTo(postalCode2);
        assertThat(postalCode1.hashCode()).isEqualTo(postalCode2.hashCode());
    }
}