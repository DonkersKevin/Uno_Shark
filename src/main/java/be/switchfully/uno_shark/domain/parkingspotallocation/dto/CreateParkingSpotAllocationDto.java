package be.switchfully.uno_shark.domain.parkingspotallocation.dto;

import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;

import java.time.LocalDateTime;

public class CreateParkingSpotAllocationDto {


    private Long userId;
    private LicensePlate licensePlate;
    private Long parkingLotId;
    private LocalDateTime startTime;

    public CreateParkingSpotAllocationDto() {
    }

    public CreateParkingSpotAllocationDto(Long userId, LicensePlate licensePlate, Long parkingLotId) {
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.parkingLotId = parkingLotId;
        this.startTime = LocalDateTime.now();
    }

    public CreateParkingSpotAllocationDto(java.lang.Long userId, LicensePlate licensePlate, Long parkingLotId, LocalDateTime startTime) {
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

}
