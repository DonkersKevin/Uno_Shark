package be.switchfully.uno_shark.domain.person.phonenumber;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LandLinePhoneTest {

    @Test
    void givenValidLandlineNumber_GivesNumber() {
        LandLinePhone mobilePhone = new LandLinePhone("053589562", CountryCode.BELGIUM);
        assertThat(mobilePhone).isNotNull();
        assertThat(mobilePhone.getLandlineBody()).isEqualTo("53589562");
        assertThat(mobilePhone.toString()).isEqualTo("+3253589562");
        assertThat(mobilePhone.getLandlineCountryCode()).isEqualTo(CountryCode.BELGIUM);
    }

    @Test
    void givenValidLandLineNumberShortNotation_GivesPhoneNumber() {
        LandLinePhone mobilePhone = new LandLinePhone("53589562", CountryCode.BELGIUM);
        assertThat(mobilePhone).isNotNull();
        assertThat(mobilePhone.getLandlineBody()).isEqualTo("53589562");
        assertThat(mobilePhone.toString()).isEqualTo("+3253589562");
        assertThat(mobilePhone.getLandlineCountryCode()).isEqualTo(CountryCode.BELGIUM);
    }

    @Test
    void givenTwoSameNumbers_AreEqual() {
        LandLinePhone phone1 = new LandLinePhone("053589562", CountryCode.BELGIUM);
        LandLinePhone phone2 = new LandLinePhone("053589562", CountryCode.BELGIUM);
        assertThat(phone1).isEqualTo(phone2);
        assertThat(phone1.hashCode()).isEqualTo(phone2.hashCode());
    }

    @Test
    void givenPhoneNumber_WhenSettingNewBodyAndCC_givenNewData() {
        LandLinePhone phone = new LandLinePhone("053589562", CountryCode.BELGIUM);
        phone.setLandlineBody("70001122");
        phone.setLandlineCountryCode(CountryCode.NETHERLANDS);
        assertThat(phone.getLandlineBody()).isEqualTo("70001122");
        assertThat(phone.getLandlineCountryCode()).isEqualTo(CountryCode.NETHERLANDS);
    }

    @Test
    void givenTooShortPhoneNumber_getException() {
        assertThatThrownBy(() -> new LandLinePhone("1234", CountryCode.BELGIUM))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Phone number too short.\nProvide a valid phone number: +32 xx xx xx xx");
    }

    @Test
    void givenNoCountryCode_getException() {
        assertThatThrownBy(() -> new LandLinePhone("54788956", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Country code has to be provided!");
    }

    @Test
    void givenNoBody_getException() {
        assertThatThrownBy(() -> new LandLinePhone(null, CountryCode.BELGIUM))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Body of phone number has to be provided!");
    }
}