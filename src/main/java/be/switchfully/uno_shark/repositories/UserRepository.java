package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLicensePlate_LicensePlateNumber(String licensePlateNumber);
    User findUserByPerson_EmailAddress(String emailAddress);
}
