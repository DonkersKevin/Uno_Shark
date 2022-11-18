package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.parking.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {


}
