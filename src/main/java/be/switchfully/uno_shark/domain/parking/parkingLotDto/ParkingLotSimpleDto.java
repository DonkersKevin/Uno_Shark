package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.domain.person.phonenumber.MobilePhone;

public class ParkingLotSimpleDto {


    private Long id;
    private String name;
    private int capacity;
    //todo fix phone
    private MobilePhone mobilePhone;

    public ParkingLotSimpleDto(Long id, String name, int capacity, MobilePhone mobilePhone) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.mobilePhone = mobilePhone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }
}
