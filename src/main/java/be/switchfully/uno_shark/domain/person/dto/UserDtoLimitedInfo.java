package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.address.Address;

import java.time.LocalDate;

public class UserDtoLimitedInfo {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String licensePlateNumber;
    private LocalDate registrationDate;

    public UserDtoLimitedInfo(Long id, String firstName, String lastName, String phoneNumber, String emailAddress, String licensePlateNumber, LocalDate registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.licensePlateNumber = licensePlateNumber;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
