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
    }
}