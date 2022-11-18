package be.switchfully.uno_shark.domain.person;

import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.phonenumber.LandLinePhone;
import be.switchfully.uno_shark.domain.person.phonenumber.MobilePhone;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

import static be.switchfully.uno_shark.domain.person.MembershipLevel.*;
import static javax.persistence.EnumType.*;

@Entity
@Table(name = "USERS")
@JsonIgnoreProperties(ignoreUnknown = true)
//todo can be used with inheritance bit we have to figure that one out
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_person_id")
    private Person person;

    //todo if we want to change this to one-to-many
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_licenseplate_id")
    private LicensePlate licensePlate;
    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Enumerated(STRING)
    @Column(name = "member_level")
    private MembershipLevel memberLevel;

    @Enumerated(STRING)
    private Role role;

    public User() {
    }

    public User(String firstName, String lastName, Address address, LandLinePhone phoneNumber, MobilePhone mobileNumber, String emailAddress) {
        this.person = new Person(firstName, lastName, address, phoneNumber, mobileNumber, emailAddress);
        this.registrationDate = LocalDate.now();
        this.role = Role.MEMBER;
    }

    public User(String firstName, String lastName, Address address, LandLinePhone phoneNumber, MobilePhone mobileNumber, String emailAddress, LicensePlate licensePlate) {
        this.person = new Person(firstName, lastName, address, phoneNumber, mobileNumber, emailAddress);
        this.registrationDate = LocalDate.now();
        this.licensePlate = licensePlate;
        this.memberLevel = BRONZE;
        this.role = Role.MEMBER;
    }

    public User(String firstName, String lastName, Address address, LandLinePhone phoneNumber, MobilePhone mobileNumber, String emailAddress, LicensePlate licensePlate, Role role) {
        this.person = new Person(firstName, lastName, address, phoneNumber, mobileNumber, emailAddress);
        this.registrationDate = LocalDate.now();
        this.licensePlate = licensePlate;
        this.role = role;
    }

    public User setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setMemberLevel(MembershipLevel memberLevel) {
        this.memberLevel = memberLevel;
    }

    public MembershipLevel getMemberLevel() {
        return memberLevel;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
