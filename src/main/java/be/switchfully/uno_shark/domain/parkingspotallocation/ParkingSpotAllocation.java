package be.switchfully.uno_shark.domain.parkingspotallocation;

import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.domain.person.User;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "parkingspotallocation")
public class ParkingSpotAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spotallocation_seq")
    @SequenceGenerator(name = "spotallocation_seq", sequenceName = "spotallocation_seq", allocationSize = 1)
    private java.lang.Long allocationId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_users_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_licensePlate_id")
    private LicensePlate licensePlate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_parkinglot_id")
    private ParkingLot parkinglot;

    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isActive;

    public ParkingSpotAllocation() {
    }

    public ParkingSpotAllocation(User user, LicensePlate licensePlate, ParkingLot parkinglot) {
        this.user = user;
        this.licensePlate = licensePlate;
        this.parkinglot = parkinglot;
    }

    public java.lang.Long getAllocationId() {
        return allocationId;
    }

    public User getUser() {
        return user;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public ParkingLot getParkinglot() {
        return parkinglot;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isActive() {
        return isActive;
    }
}
