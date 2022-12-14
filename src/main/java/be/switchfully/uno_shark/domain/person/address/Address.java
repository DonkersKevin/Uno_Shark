package be.switchfully.uno_shark.domain.person.address;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
    private Long id;
    @Column(name = "streetname")
    private String streetName;
    @Column(name = "housenumber")
    private String houseNumber;
    @Embedded
    private PostalCode postalCode;
    @Column(name = "country")
    private String country;

    public Address() {

    }

    public Address(Long id, String streetName, String houseNumber, PostalCode postalCode, String country) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Address(String streetName, String houseNumber, PostalCode postalCode, String country) {
        this.streetName = streetVerification(streetName);
        this.houseNumber = houseNumberVerification(houseNumber);
        this.postalCode = postalCodeVerification(postalCode);
        this.country = countryVerification(country);
    }

    private String streetVerification(String streetName) {
        if (streetName == null) {
            throw new IllegalArgumentException("Provide a streetname.");
        }
        return streetName;
    }

    private String houseNumberVerification(String houseNumber) {
        if (houseNumber == null) {
            throw new IllegalArgumentException("Provide a house number.");
        }
        return houseNumber;
    }

    private PostalCode postalCodeVerification(PostalCode postalCode) {
        if (postalCode == null) {
            throw new IllegalArgumentException("Provide a postal code.");
        }
        return postalCode;
    }

    private String countryVerification(String country) {
        if (country == null) {
            throw new IllegalArgumentException("Provide a country.");
        }
        return country;
    }

    public Long getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode=" + postalCode +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return Objects.equals(streetName, address.streetName) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(postalCode, address.postalCode) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, houseNumber, postalCode, country);
    }
}
