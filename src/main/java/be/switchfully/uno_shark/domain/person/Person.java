package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.phonenumber.LandLinePhone;
import be.switchfully.uno_shark.domain.person.phonenumber.MobilePhone;

import javax.persistence.*;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1)

    private Long id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_address_id")
    private Address address;

    @Embedded
    private LandLinePhone landLinePhone;

    @Embedded
    private MobilePhone mobilePhone;
    private String emailAddress;

    public Person() {
    }

    public Person(String firstName, String lastName, Address address, LandLinePhone landLinePhone, MobilePhone mobilePhone, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.landLinePhone = landLinePhone;
        this.mobilePhone = mobilePhone;
        this.emailAddress = emailAddress;
        checkPhoneNumber();
    }

    public Person(Long id, String firstName, String lastName, Address address, LandLinePhone landLinePhone, MobilePhone mobilePhone, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.landLinePhone = landLinePhone;
        this.mobilePhone = mobilePhone;
        this.emailAddress = emailAddress;
    }

    private void checkPhoneNumber(){
        if(this.landLinePhone == null && this.mobilePhone == null){
            throw new IllegalArgumentException("No phone number found.");
        }
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

    public Person setLandLinePhone(LandLinePhone phoneNumber) {
        this.landLinePhone = phoneNumber;
        return this;
    }

    public Person setMobilePhone(MobilePhone mobileNumber) {
        this.mobilePhone = mobileNumber;
        return this;
    }

    public Person setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
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

    public LandLinePhone getLandLinePhone() {
        return landLinePhone;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
