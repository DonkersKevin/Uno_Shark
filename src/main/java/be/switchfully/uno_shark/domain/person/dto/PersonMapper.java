package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonDto persontoDto(Person person){
        return new PersonDto(
          person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getPhoneNumber(),
                person.getMobileNumber(),
                person.getEmailAddress()
        );
    }

    public Person dtoToPerson(PersonDto personDto){
        return new Person(
                personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getAddress(),
                personDto.getPhoneNumber(),
                personDto.getMobileNumber(),
                personDto.getEmailAddress()
        );
    }
}
