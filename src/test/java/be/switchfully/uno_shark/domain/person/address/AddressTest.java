package be.switchfully.uno_shark.domain.person.address;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AddressTest {

    @Test
    void givenvalidAddress_noExceptionsThrown() {
        Address address = new Address("Straat", "125", new PostalCode("1000", "Brussel"), "BELGIUM");
        assertThat(address).isNotNull();
        assertThat(address.getStreetName()).isEqualTo("Straat");
        assertThat(address.getHouseNumber()).isEqualTo("125");
        assertThat(address.getPostalCode()).isEqualTo(new PostalCode("1000", "Brussel"));
        assertThat(address.getCountry()).isEqualTo("BELGIUM");
    }

    @Test
    void givenAddressWithoutStreetName_ThrowsException() {
        assertThatThrownBy(() -> new Address(null, "125", new PostalCode("1000", "Brussel"), "BELGIUM"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a streetname.");
    }

    @Test
    void givenAddressWithoutHouseNumber_ThrowsException() {
        assertThatThrownBy(() -> new Address("Straat", null, new PostalCode("1000", "Brussel"), "BELGIUM"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a house number.");
    }

    @Test
    void givenAddressWithoutPostalCode_ThrowsException() {
        assertThatThrownBy(() -> new Address("Straat", "125", null, "BELGIUM"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a postal code.");
    }

    @Test
    void givenAddressWithoutCountry_ThrowsException() {
        assertThatThrownBy(() -> new Address("Straat", "125", new PostalCode("1000", "Brussel"), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provide a country.");
    }

    @Test
    void givenTwoSameAddresses_IsEqual() {
        Address address1 = new Address("Straat", "125", new PostalCode("1000", "Brussel"), "BELGIUM");
        Address address2 = new Address("Straat", "125", new PostalCode("1000", "Brussel"), "BELGIUM");
        assertThat(address1).isEqualTo(address2);
        assertThat(address1.hashCode()).isEqualTo(address2.hashCode());
    }
}