package be.switchfully.uno_shark.domain.person.phonenumber;

public class MobilePhone {
    private String body;
    private CountryCode countryCode;

    public MobilePhone(String body, CountryCode countryCode) {
        this.body = body;
        this.countryCode = countryCode;
        numberVerification(this);
    }

    private void numberVerification(MobilePhone phoneNumber) {
        if (phoneNumber.getCountryCode() == null) {
            throw new IllegalArgumentException("Country code has to be provided!");
        }
        verificationBelgiumNumber(phoneNumber);
    }

    private static void verificationBelgiumNumber(MobilePhone phoneNumber) {
        if (phoneNumber.getCountryCode().equals(CountryCode.BELGIUM)) {
            if (phoneNumber.getBody().length() < 9) {
                throw new IllegalArgumentException("Provide a valid phone number: +32 4xx xx xx xx");
            }
            if (phoneNumber.getBody().charAt(0) == '0') {
                String oldBody = phoneNumber.getBody();
                phoneNumber.setBody(oldBody.substring(1));
            }
            if (phoneNumber.getBody().charAt(0) != '4') {
                throw new IllegalArgumentException("Provide a valid phone number: +32 4xx xx xx xx");
            }
        }
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return countryCode.getCountryCode() + body;
    }
}
