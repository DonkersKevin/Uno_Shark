package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.parking.ParkingCategory;
import be.switchfully.uno_shark.domain.parking.Price;
import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.person.Person;
import be.switchfully.uno_shark.domain.person.address.Address;

public class ParkingLotDto {

    private Long id;
    private String name;
    private ParkingCategory parkingCategory;
    private ShowDivisionDto division;
    private int capacity;
    private Person Person;
    private Address address;
    private Price pricePerHour;



    public ParkingLotDto(Long id, String name, ParkingCategory parkingCategory, ShowDivisionDto division, int capacity, Person person, Address address, Price pricePerHour) {
        this.id = id;
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.division = division;
        this.capacity = capacity;
        this.Person = person;
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

    public ShowDivisionDto getDivision() {
        return division;
    }

    public int getCapacity() {
        return capacity;
    }

    public Person getPerson() {
        return Person;
    }

    public Address getAddress() {
        return address;
    }

    public Price getPricePerHour() {
        return pricePerHour;
    }
}
