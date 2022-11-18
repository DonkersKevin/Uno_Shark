package be.switchfully.uno_shark.domain.person.phonenumber;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class LandLinePhone {
    @Enumerated(EnumType.STRING)
    private CountryCode landlineCountryCode;
    private String landlineBody;


    public LandLinePhone() {
    }

    public LandLinePhone(String landlineBody, CountryCode landlineCountryCode) {
        String newBody = numberVerification(landlineCountryCode, landlineBody);
        this.landlineCountryCode = landlineCountryCode;
        this.landlineBody = newBody;
    }

    private String numberVerification(CountryCode countryCode, String body) {
        if (countryCode == null) {
            throw new IllegalArgumentException("Country code has to be provided!");
        }
        if (body == null) {
            throw new IllegalArgumentException("Body of phone number has to be provided!");
        }
        return verificationBelgiumNumber(countryCode, body);
    }

    private String verificationBelgiumNumber(CountryCode countryCode, String body) {
        if (countryCode.equals(CountryCode.BELGIUM)) {
            if (body.length() < 8) {
                throw new IllegalArgumentException("Phone number too short.\nProvide a valid phone number: +32 xx xx xx xx");
            }
            if (body.charAt(0) == '0') {
                return body.substring(1);
            }
        }
        return body;
    }

    public CountryCode getLandlineCountryCode() {
        return landlineCountryCode;
    }

    public void setLandlineCountryCode(CountryCode countryCode) {
        this.landlineCountryCode = countryCode;
    }

    public String getLandlineBody() {
        return landlineBody;
    }

    public void setLandlineBody(String body) {
        this.landlineBody = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LandLinePhone that = (LandLinePhone) o;
        return landlineCountryCode == that.landlineCountryCode && Objects.equals(landlineBody, that.landlineBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(landlineCountryCode, landlineBody);
    }

    @Override
    public String toString() {
        return landlineCountryCode.getCountryCode() + landlineBody;
    }
}
