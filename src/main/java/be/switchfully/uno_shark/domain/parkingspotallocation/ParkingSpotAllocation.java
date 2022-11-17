package be.switchfully.uno_shark.domain.parkingspotallocation;

import be.switchfully.uno_shark.domain.parking.Parkinglot;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.domain.person.User;

import javax.persistence.*;

@Entity
@Table(name = "parkingspotallocation")
public class ParkingSpotAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spotallocation_seq")
    @SequenceGenerator(name = "spotallocation_seq", sequenceName = "spotallocation_seq", allocationSize = 1)
    private Long allocationId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_users_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_licensePlate_id")
    private LicensePlate licensePlate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_parkinglot_id")
    private Parkinglot parkinglot;

    public ParkingSpotAllocation(User user, LicensePlate licensePlate, Parkinglot parkinglot) {
        this.user = user;
        this.licensePlate = licensePlate;
        this.parkinglot = parkinglot;
        //verifyParkingSpotAllocation(this);
    }

    public ParkingSpotAllocation() {
    }
}
