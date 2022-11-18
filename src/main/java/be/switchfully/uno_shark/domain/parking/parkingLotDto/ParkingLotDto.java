package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.parking.ParkingCategory;
import be.switchfully.uno_shark.domain.parking.Price;
import be.switchfully.uno_shark.domain.parking.divisionDto.SingleDivisionDto;
import be.switchfully.uno_shark.domain.person.Person;
import be.switchfully.uno_shark.domain.person.address.Address;

import java.util.Objects;

public class ParkingLotDto {
    private Long id;
    private String name;
    private ParkingCategory parkingCategory;
    private SingleDivisionDto division;
    private int capacity;
    private Person Person;
    private Address address;
    private Price pricePerHour;

    public ParkingLotDto(Long id, String name, ParkingCategory parkingCategory, SingleDivisionDto division, int capacity, Person person, Address address, Price pricePerHour) {
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

    public SingleDivisionDto getDivision() {
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

    @Override
    public String toString() {
        return "ParkingLotDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parkingCategory=" + parkingCategory +
                ", division=" + division +
                ", capacity=" + capacity +
                ", Person=" + Person +
                ", address=" + address +
                ", pricePerHour=" + pricePerHour +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLotDto that = (ParkingLotDto) o;
        return capacity == that.capacity && Objects.equals(id, that.id) && Objects.equals(name, that.name) && parkingCategory == that.parkingCategory && Objects.equals(division, that.division) && Objects.equals(Person, that.Person) && Objects.equals(address, that.address) && Objects.equals(pricePerHour, that.pricePerHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parkingCategory, division, capacity, Person, address, pricePerHour);
    }
}
