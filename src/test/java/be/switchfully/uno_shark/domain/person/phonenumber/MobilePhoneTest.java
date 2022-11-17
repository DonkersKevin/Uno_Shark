package be.switchfully.uno_shark.domain.person.phonenumber;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MobilePhoneTest {

    @Test
    void givenValidPhoneNumber_GivesPhoneNumber() {
        MobilePhone mobilePhone = new MobilePhone("0475849562", CountryCode.BELGIUM);
        assertThat(mobilePhone).isNotNull();
        assertThat(mobilePhone.getBody()).isEqualTo("475849562");
        assertThat(mobilePhone.toString()).isEqualTo("+32475849562");
        assertThat(mobilePhone.getCountryCode()).isEqualTo(CountryCode.BELGIUM);
    }

    @Test
    void givenValidPhoneNumberShortNotation_GivesPhoneNumber() {
        MobilePhone mobilePhone = new MobilePhone("475849562", CountryCode.BELGIUM);
        assertThat(mobilePhone).isNotNull();
        assertThat(mobilePhone.getBody()).isEqualTo("475849562");
        assertThat(mobilePhone.toString()).isEqualTo("+32475849562");
        assertThat(mobilePhone.getCountryCode()).isEqualTo(CountryCode.BELGIUM);
    }

    @Test
    void givenTooShortPhoneNumber_getException() {
        assertThatThrownBy(() -> new MobilePhone("1234", CountryCode.BELGIUM))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Phone number too short.\nProvide a valid phone number: +32 4xx xx xx xx");
    }

    @Test
    void givenNoCountryCode_getException() {
        assertThatThrownBy(() -> new MobilePhone("478121518", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Country code has to be provided!");
    }

    @Test
    void givenNoBody_getException() {
        assertThatThrownBy(() -> new MobilePhone(null, CountryCode.BELGIUM))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Body of phone number has to be provided!");
    }

    @Test
    void givenBodyNotWith4WhileBelgiumNumber_getException() {
        assertThatThrownBy(() -> new MobilePhone("597485963", CountryCode.BELGIUM))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Wrong format phone number.\nProvide a valid phone number: +32 4xx xx xx xx");
    }

    @Test
    void givenBodyNotWith4WhileBelgiumLongNumber_getException() {
        assertThatThrownBy(() -> new MobilePhone("0597485963", CountryCode.BELGIUM))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Wrong format phone number.\nProvide a valid phone number: +32 4xx xx xx xx");
    }
}