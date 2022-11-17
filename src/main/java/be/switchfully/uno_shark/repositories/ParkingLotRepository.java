package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.parking.Parkinglot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends JpaRepository<Parkinglot, Long> {
}
