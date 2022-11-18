package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.person.phonenumber.LandLinePhone;
import be.switchfully.uno_shark.domain.person.phonenumber.MobilePhone;

public class ParkingLotSimpleDto {


    private Long id;
    private String name;
    private int capacity;
    private MobilePhone mobilePhone;

    private LandLinePhone landLinePhone;

    public ParkingLotSimpleDto(Long id, String name, int capacity, MobilePhone mobilePhone, LandLinePhone landLinePhone) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.mobilePhone = mobilePhone;
        this.landLinePhone = landLinePhone;
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

    public LandLinePhone getLandLinePhone() {
        return landLinePhone;
    }
}
