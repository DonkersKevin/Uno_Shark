package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.address.Address;

import java.time.LocalDate;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;
    private LicensePlate licensePlate;
    private LocalDate registrationDate;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }



    public CreateUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateUserDto setAddress(Address address) {
        this.address = address;
        return this;
    }

    public CreateUserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CreateUserDto setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public CreateUserDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public CreateUserDto setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public CreateUserDto setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
