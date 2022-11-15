package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.address.Address;

import java.time.LocalDate;

public class User extends Person{

    private LicensePlate licensePlate;
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
