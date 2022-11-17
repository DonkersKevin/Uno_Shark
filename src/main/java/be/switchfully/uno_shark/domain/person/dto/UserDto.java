package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.MembershipLevel;
import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.security.Role;

import java.time.LocalDate;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String mobileNumber;
    private String emailAddress;
    private LicensePlate licensePlate;
    private LocalDate registrationDate;
    private MembershipLevel memberLevel;

    private Role role;
    private String userName;


    public MembershipLevel getMemberLevel() {
        return memberLevel;
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

    public Role getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public UserDto setId(Long id) {
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

    public UserDto setRole(Role role) {
        this.role = role;
        return this;
    }

    public UserDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
