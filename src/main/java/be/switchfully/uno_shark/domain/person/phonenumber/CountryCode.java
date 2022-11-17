package be.switchfully.uno_shark.domain.person.phonenumber;

public enum CountryCode {
    BELGIUM("+32"),
    FRANCE("+33"),
    NETHERLANDS("+31");

    private String countryCode;

    CountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
