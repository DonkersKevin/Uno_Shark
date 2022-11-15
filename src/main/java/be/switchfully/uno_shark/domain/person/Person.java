package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.address.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


public class Person {

    private String firstName;
    private String lastName;

    @OneToOne
    @JoinColumn(name = "fk_address_id")
    private Address address;

    private String phoneNumber;

    private String mobileNumber;
    private String emailAddress;


    public Person() {
    }

    public Person(String firstName, String lastName, Address address, String phoneNumber, String mobileNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Person setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Person setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Person setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public Person setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
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
}
