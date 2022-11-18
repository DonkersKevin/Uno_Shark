package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.phonenumber.LandLinePhone;
import be.switchfully.uno_shark.domain.person.phonenumber.MobilePhone;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
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

    //check if we can still use the builder pattern and inheritance altogether.
    public Person(String firstName, String lastName, Address address, LandLinePhone landLinePhone, MobilePhone mobilePhone, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.landLinePhone = landLinePhone;
        this.mobilePhone = mobilePhone;
        this.emailAddress = emailAddress;
        checkPhoneNumber();
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

    public long getId() {
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", phoneNumber='" + landLinePhone + '\'' +
                ", mobileNumber='" + mobilePhone + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(landLinePhone, person.landLinePhone) && Objects.equals(mobilePhone, person.mobilePhone) && Objects.equals(emailAddress, person.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, landLinePhone, mobilePhone, emailAddress);
    }
}
