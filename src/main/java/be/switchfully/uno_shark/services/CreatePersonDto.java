package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.phonenumber.LandLinePhone;
import be.switchfully.uno_shark.domain.person.phonenumber.MobilePhone;

public class CreatePersonDto {

    private String firstName;
    private String lastName;
    private Address address;
    private LandLinePhone phoneNumber;
    private MobilePhone mobileNumber;
    private String emailAddress;

    public CreatePersonDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public LandLinePhone getPhoneNumber() {
        return phoneNumber;
    }

    public MobilePhone getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public CreatePersonDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreatePersonDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreatePersonDto setAddress(Address address) {
        this.address = address;
        return this;
    }

    public CreatePersonDto setPhoneNumber(LandLinePhone phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CreatePersonDto setMobileNumber(MobilePhone mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public CreatePersonDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
}
