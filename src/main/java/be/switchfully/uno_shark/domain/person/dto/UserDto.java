package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.MembershipLevel;
import be.switchfully.uno_shark.domain.person.Role;
import be.switchfully.uno_shark.domain.person.address.Address;

import java.time.LocalDate;

public class UserDto {

    private java.lang.Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;
    private LicensePlate licensePlate;
    private LocalDate registrationDate;

    private Role role;

    private MembershipLevel memberLevel;

    public UserDto(Long id, String firstName, String lastName, Address address, String phoneNumber, String mobileNumber, String emailAddress, LicensePlate licensePlate, LocalDate registrationDate, Role role, MembershipLevel memberLevel) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.licensePlate = licensePlate;
        this.registrationDate = registrationDate;
        this.role = role;
        this.memberLevel = memberLevel;
    }

    public UserDto() {
    }

    public MembershipLevel getMemberLevel() {
        return memberLevel;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public java.lang.Long getId() {
        return id;
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

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public UserDto setId(java.lang.Long id) {
        this.id = id;
        return this;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDto setAddress(Address address) {
        this.address = address;
        return this;
    }

    public UserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDto setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public UserDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserDto setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public UserDto setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public UserDto setMemberLevel(MembershipLevel memberLevel) {
        this.memberLevel = memberLevel;
        return this;
    }
}
