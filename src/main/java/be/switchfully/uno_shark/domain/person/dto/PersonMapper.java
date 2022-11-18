package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.Person;
import be.switchfully.uno_shark.services.CreatePersonDto;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonDto persontoDto(Person person) {
        return new PersonDto(
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getLandLinePhone().toString(),
                person.getMobilePhone().toString(),
                person.getEmailAddress()
        );
    }

    public Person dtoToPerson(CreatePersonDto createPersonDto) {
        return new Person(
                createPersonDto.getFirstName(),
                createPersonDto.getLastName(),
                createPersonDto.getAddress(),
                createPersonDto.getLandLinePhone(),
                createPersonDto.getMobilePhone(),
                createPersonDto.getEmailAddress()
        );
    }
}
