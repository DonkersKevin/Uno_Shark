package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.parkingspotallocation.ParkingSpotAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {


}
