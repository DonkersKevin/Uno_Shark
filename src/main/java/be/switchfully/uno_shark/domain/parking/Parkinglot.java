package be.switchfully.uno_shark.domain.parking;


import be.switchfully.uno_shark.domain.parking.util.Price;

import javax.persistence.*;

@Entity
@Table(name = "parkinglot")
public class Parkinglot {

    //Todo verify identity annotation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ParkingCategory parkingCategory;
    private Long divisionId;
    private int capacity;
    private Long contactPersonId;
    private Long addressId;

    @Embedded
    private Price pricePerHour;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ParkingCategory getParkingCategory() {
        return parkingCategory;
    }

    public Long getDivisionId() {
        return divisionId;
    }

    public int getCapacity() {
        return capacity;
    }

    public Long getContactPersonId() {
        return contactPersonId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Price getPricePerHour() {
        return pricePerHour;
    }
}
