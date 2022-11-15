package be.switchfully.uno_shark.domain.person;

import java.time.LocalDate;

public class User extends Person{

    private LicensePlate licensePlate;
    private LocalDate registrationDate;



    public User() {
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
