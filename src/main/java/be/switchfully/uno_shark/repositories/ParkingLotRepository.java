package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.parking.Parkinglot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends CrudRepository<Parkinglot, Long> {
}
