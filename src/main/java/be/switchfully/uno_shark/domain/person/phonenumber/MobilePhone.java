package be.switchfully.uno_shark.domain.person.phonenumber;

public class MobilePhone {
    private final String body;
    private final CountryCode countryCode;

    public MobilePhone(String body, CountryCode countryCode) {
        String newBody = numberVerification(countryCode, body);
        this.body = newBody;
        this.countryCode = countryCode;
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

    public String getBody() {
        return body;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    @Override
    public String toString() {
        return countryCode.getCountryCode() + body;
    }
}
