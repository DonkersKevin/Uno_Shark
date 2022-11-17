package be.switchfully.uno_shark.domain.parking;

import be.switchfully.uno_shark.domain.person.Person;
import be.switchfully.uno_shark.domain.person.address.Address;

import javax.persistence.*;

@Entity
@Table(name = "parkinglot")
public class Parkinglot {

    //Todo verify identity annotation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkinglot_seq")
    @SequenceGenerator(name = "parkinglot_seq", sequenceName = "parkinglot_seq", allocationSize = 1)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ParkingCategory parkingCategory;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_division_id")
    private Division division;
    private int capacity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_person_id")
    private Person person;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_address_id")
    private Address address;

    @Embedded
    private Price pricePerHour;

    public Parkinglot() {
    }

    public Parkinglot(Long id, String name, ParkingCategory parkingCategory, Division division, int capacity, Person person, Address address, Price pricePerHour) {
        this.id = id;
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.division = division;
        this.capacity = capacity;
        this.person = person;
        this.address = address;
        this.pricePerHour = pricePerHour;
    }

    public Parkinglot(String name, ParkingCategory parkingCategory, Division division, int capacity, Person person, Address address, Price pricePerHour) {
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.division = division;
        this.capacity = capacity;
        this.person = person;
        this.address = address;
        this.pricePerHour = pricePerHour;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ParkingCategory getParkingCategory() {
        return parkingCategory;
    }

    public Division getDivision() {
        return division;
    }

    public int getCapacity() {
        return capacity;
    }

    public Person getPerson() {
        return person;
    }

    public Address getAddress() {
        return address;
    }

    public Price getPricePerHour() {
        return pricePerHour;
    }
}
