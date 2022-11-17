package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.parking.ParkingCategory;
import be.switchfully.uno_shark.domain.parking.Price;
import be.switchfully.uno_shark.domain.parking.divisionDto.CreateDivisionDto;
import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.dto.PersonDto;

public class CreateParkingLotDto {

    private String name;
    private ParkingCategory parkingCategory;
    private CreateDivisionDto division;
    private int capacity;
    private PersonDto person;
    private Address address;
    private Price pricePerHour;

    public CreateParkingLotDto(String name, ParkingCategory parkingCategory, CreateDivisionDto division, int capacity, PersonDto person, Address address, Price pricePerHour) {
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

    public CreateDivisionDto getDivision() {
        return division;
    }

    public int getCapacity() {
        return capacity;
    }

    public PersonDto getPerson() {
        return person;
    }

    public Address getAddress() {
        return address;
    }

    public Price getPricePerHour() {
        return pricePerHour;
    }
}
