package be.switchfully.uno_shark.domain.parkingspotallocation.dto;

import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;

import java.time.LocalTime;

public class CreateParkingSpotAllocationDto {


    private Long userId;
    private LicensePlate licensePlate;
    private Long parkingLotId;
    private LocalTime startTime;


    public CreateParkingSpotAllocationDto(java.lang.Long userId, LicensePlate licensePlate, Long parkingLotId) {
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.parkingLotId = parkingLotId;
        this.startTime = LocalTime.now();
    }

    public CreateParkingSpotAllocationDto(java.lang.Long userId, LicensePlate licensePlate, Long parkingLotId, LocalTime startTime) {
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.parkingLotId = parkingLotId;
        this.startTime = startTime;
    }


    public java.lang.Long getUserId() {
        return userId;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

}
