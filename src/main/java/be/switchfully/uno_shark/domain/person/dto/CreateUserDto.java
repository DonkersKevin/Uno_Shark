package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.domain.person.MembershipLevel;
import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.phonenumber.LandLinePhone;
import be.switchfully.uno_shark.domain.person.phonenumber.MobilePhone;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private Address address;
    private LandLinePhone phoneNumber;
    private MobilePhone mobileNumber;
    private String emailAddress;
    private LicensePlate licensePlate;

    private MembershipLevel memberLevel;


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

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public MembershipLevel getMemberLevel() {
        return memberLevel;
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

    public CreateUserDto setPhoneNumber(LandLinePhone phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CreateUserDto setMobileNumber(MobilePhone mobileNumber) {
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

    public CreateUserDto setMemberLevel(MembershipLevel memberLevel) {
        this.memberLevel = memberLevel;
        return this;
    }


}
