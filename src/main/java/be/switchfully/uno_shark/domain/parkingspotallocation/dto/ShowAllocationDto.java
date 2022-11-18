package be.switchfully.uno_shark.domain.parkingspotallocation.dto;

import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;

import java.time.LocalTime;

public class ShowAllocationDto implements Comparable<ShowAllocationDto>{

    private Long id;
    private Long userId;
    private LicensePlate licensePlate;
    private Long parkingLotId;
    private LocalTime startTime;
    private LocalTime stopTime;

    public ShowAllocationDto(Long id, Long userId, LicensePlate licensePlate, Long parkingLotId, LocalTime startTime) {
        this.id = id;
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.parkingLotId = parkingLotId;
        this.startTime = startTime;
    }

    public void setStopTime(LocalTime stopTime) {
        this.stopTime = stopTime;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
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

    public LocalTime getStopTime() {
        return stopTime;
    }

    @Override
    public int compareTo(ShowAllocationDto o) {
        return startTime.compareTo(o.startTime);
    }
}
