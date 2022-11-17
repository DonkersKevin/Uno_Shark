package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.licenseplate.IssuingCountry;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LicensePlateTest {

    @Test
    void givenValidLicensePlate_getNoError() {
        LicensePlate licensePlate = new LicensePlate(IssuingCountry.BE,"XOXO");
        assertThat(licensePlate).isNotNull();
        assertThat(licensePlate.getIssuingCountry()).isEqualTo(IssuingCountry.BE);
        assertThat(licensePlate.getLicensePlateNumber()).isEqualTo("XOXO");
    }

    @Test
    void givenTwoSameLicensePlate_IsBothEqual() {
        LicensePlate licensePlate1 = new LicensePlate(IssuingCountry.BE,"XOXO");
        LicensePlate licensePlate2 = new LicensePlate(IssuingCountry.BE,"XOXO");
        assertThat(licensePlate1).isEqualTo(licensePlate2);
        assertThat(licensePlate1.hashCode()).isEqualTo(licensePlate2.hashCode());
    }

    @Test
    void givenLicensePlateWithoutCountry_throwsException() {
        assertThatThrownBy(() -> new LicensePlate(null, "XOXO"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Issuing country has to be provided.");
    }

    @Test
    void givenLicensePlateWithoutPlateNumber_throwsException() {
        assertThatThrownBy(() -> new LicensePlate(IssuingCountry.BE, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("License plate number has to be provided.");
    }
}