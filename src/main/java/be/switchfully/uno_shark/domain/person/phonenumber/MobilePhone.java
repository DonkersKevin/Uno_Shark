package be.switchfully.uno_shark.domain.person.phonenumber;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class MobilePhone {
    private String mobilePhoneBody;
    @Enumerated(EnumType.STRING)
    private CountryCode mobilePhoneCountryCode;

    public MobilePhone() {
    }

    public MobilePhone(String mobilePhoneBody, CountryCode mobilePhoneCountryCode) {
        String newBody = numberVerification(mobilePhoneCountryCode, mobilePhoneBody);
        this.mobilePhoneBody = newBody;
        this.mobilePhoneCountryCode = mobilePhoneCountryCode;
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
            if (body.length() < 9) {
                throw new IllegalArgumentException("Phone number too short.\nProvide a valid phone number: +32 4xx xx xx xx");
            }
            if (body.charAt(0) == '0') {
                String newBody = body.substring(1);
                if (newBody.charAt(0) != '4') {
                    throw new IllegalArgumentException("Wrong format phone number.\nProvide a valid phone number: +32 4xx xx xx xx");
                }
                return newBody;
            }
            if (body.charAt(0) != '4') {
                throw new IllegalArgumentException("Wrong format phone number.\nProvide a valid phone number: +32 4xx xx xx xx");
            }
        }
        return body;
    }

    public String getMobilePhoneBody() {
        return mobilePhoneBody;
    }

    public CountryCode getMobilePhoneCountryCode() {
        return mobilePhoneCountryCode;
    }

    public void setMobilePhoneBody(String body) {
        this.mobilePhoneBody = body;
    }

    public void setMobilePhoneCountryCode(CountryCode countryCode) {
        this.mobilePhoneCountryCode = countryCode;
    }

    @Override
    public String toString() {
        return mobilePhoneCountryCode.getCountryCode() + mobilePhoneBody;
    }
}
