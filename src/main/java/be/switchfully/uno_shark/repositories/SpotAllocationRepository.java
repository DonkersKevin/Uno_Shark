package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.parkingspotallocation.ParkingSpotAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotAllocationRepository extends JpaRepository<ParkingSpotAllocation, Long> {
    //ParkingSpotAllocation findParkingSpotAllocationByUserId(String userId);
    //ParkingSpotAllocation findParkingSpotAllocationsByUserId(Long userId);
    //ParkingSpotAllocation findParkingSpotAllocationsByParkinglotId(Long parkingLotId);
    //List<ParkingSpotAllocation> findByActiveFalse();
    //List<ParkingSpotAllocation> findByActiveTrue();

}
