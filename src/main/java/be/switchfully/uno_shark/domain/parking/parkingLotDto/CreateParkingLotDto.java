package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.parking.Division;
import be.switchfully.uno_shark.domain.parking.ParkingCategory;
import be.switchfully.uno_shark.domain.parking.Price;
import be.switchfully.uno_shark.domain.person.Person;
import be.switchfully.uno_shark.domain.person.address.Address;

public class CreateParkingLotDto {

    private String name;
    private ParkingCategory parkingCategory;
    private Division division;
    private int capacity;
    private Person person;
    private Address address;
    private Price pricePerHour;

    public CreateParkingLotDto(String name, ParkingCategory parkingCategory, Division division, int capacity, Person person, Address address, Price pricePerHour) {
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.division = division;
        this.capacity = capacity;
        this.person = person;
        this.address = address;
        this.pricePerHour = pricePerHour;
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
