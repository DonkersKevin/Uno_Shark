package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.address.Address;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
//todo can be used with inheritance bit we have to figure that one out
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_person_id")
    private Person person;

    //todo if we want to change this to one-to-many
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_licenseplate_id")
    private LicensePlate licensePlate;
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public User() {
    }

    public User (String firstName, String lastName, Address address, String phoneNumber, String mobileNumber, String emailAddress) {
        this.person = new Person(firstName, lastName, address, phoneNumber, mobileNumber, emailAddress);
        this.registrationDate = LocalDate.now();
    }

    public User (String firstName, String lastName, Address address, String phoneNumber, String mobileNumber, String emailAddress, LicensePlate licensePlate) {
        this.person = new Person(firstName, lastName, address, phoneNumber, mobileNumber, emailAddress);
        this.registrationDate = LocalDate.now();
        this.licensePlate = licensePlate;
    }

    public User setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }
    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Person getPerson() {
        return person;
    }
}
