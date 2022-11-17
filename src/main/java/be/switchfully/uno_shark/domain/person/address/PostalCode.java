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
        this.postalCode = postalCode;
        this.city = city;
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
