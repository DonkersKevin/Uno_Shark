package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.address.Address;


public class PersonDto {

    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;

    public PersonDto() {
    }

    public PersonDto(String firstName, String lastName, Address address, String phoneNumber, String mobileNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public PersonDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonDto setAddress(Address address) {
        this.address = address;
        return this;
    }

    public PersonDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PersonDto setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public PersonDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }
}
