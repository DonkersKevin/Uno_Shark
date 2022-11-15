package be.switchfully.uno_shark.repositories;

import be.switchfully.uno_shark.domain.person.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


}
