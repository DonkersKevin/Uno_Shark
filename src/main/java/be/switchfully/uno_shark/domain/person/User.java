package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.address.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
public class User extends Person{

    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    public User() {
    }

    public User (String firstName, String lastName, Address address, String phoneNumber, String mobileNumber, String emailAddress) {
        super(firstName, lastName, address, phoneNumber, mobileNumber, emailAddress);
    }

    public User setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public User setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
