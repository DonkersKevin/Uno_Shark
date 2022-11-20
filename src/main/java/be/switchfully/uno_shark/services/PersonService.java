package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.person.Person;
import be.switchfully.uno_shark.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person checkPersonForDuplicatesByFields(Person person) {
        log.info("Checking database for existing person based on fields.");
        Example<Person> example = Example.of(person);
        Optional<Person> searchresult = personRepository.findOne(example);
        return searchresult.orElse(person);
    }
}
