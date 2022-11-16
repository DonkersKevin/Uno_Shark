package be.switchfully.uno_shark.domain.person.address;
import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
    private long id;
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

    public Address(long id, String streetName, String houseNumber, PostalCode postalCode, String country) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Address(String streetName, String houseNumber, PostalCode postalCode, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.country = country;
    }

    public long getId() {
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
}
