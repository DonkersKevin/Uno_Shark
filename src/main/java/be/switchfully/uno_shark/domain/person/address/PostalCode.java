package be.switchfully.uno_shark.domain.person.address;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PostalCode {
    private String postalCode;
    private String city;

    public PostalCode() {
    }

    public PostalCode(String postalCode, String city) {
        this.postalCode = postalcodeVerification(postalCode);
        this.city = cityVerification(city);
    }

    private String postalcodeVerification(String postalCode) {
        if (postalCode == null) {
            throw new IllegalArgumentException("Postal Code cannot be empty.");
        }
        if (postalCode.length() != 4) {
            throw new IllegalArgumentException("Provide a valid postal code: format ####");
        }
        return postalCode;
    }

    private String cityVerification(String possibleCity) {
        if (possibleCity == null) {
            throw new IllegalArgumentException("Provide a city name.");
        }
        return possibleCity;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostalCode that = (PostalCode) o;
        return Objects.equals(postalCode, that.postalCode) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode, city);
    }

    @Override
    public String toString() {
        return "PostalCode{" +
                "postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
