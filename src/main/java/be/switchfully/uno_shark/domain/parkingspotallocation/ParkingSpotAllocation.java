package be.switchfully.uno_shark.domain.parkingspotallocation;

import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.domain.person.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parkingspotallocation")
public class ParkingSpotAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spotallocation_seq")
    @SequenceGenerator(name = "spotallocation_seq", sequenceName = "spotallocation_seq", allocationSize = 1)
    private Long id;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_users_id")
    private User user;

    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "fk_licenseplate_id")
    private LicensePlate licensePlate;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_parkinglot_id")
    private ParkingLot parkinglot;

    @Column(name = "starttime")
    private LocalDateTime startTime;
    @Column(name = "endtime")
    private LocalDateTime endTime;
    @Column(name = "isactive")
    private boolean isActive;

    public ParkingSpotAllocation() {
    }


    public ParkingSpotAllocation(User user, LicensePlate licensePlate, ParkingLot parkinglot) {
        this.user = user;
        this.licensePlate = licensePlate;
        this.parkinglot = parkinglot;
        this.startTime = LocalDateTime.now();
        this.isActive = true;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void stop(){
        if(!isActive) throw new IllegalArgumentException("This allocation has already been stopped!");
        isActive = false;
        endTime = LocalDateTime.now();
    }

}
