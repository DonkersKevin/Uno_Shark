package be.switchfully.uno_shark.services.util;

import be.switchfully.uno_shark.domain.person.dto.PersonDto;
import be.switchfully.uno_shark.services.GeneralValidationService;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {

    private GeneralValidationService validationService;

    public PersonValidator(GeneralValidationService validationService) {
        this.validationService = validationService;
    }

    public void isValidEmail(String emailAddress) {
        if (!emailAddress.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email address is not valid");
        }
    }

    public void checkRequiredFields(PersonDto personDto) {
        validationService.assertNotNullOrBlank(personDto.getEmailAddress(), "Email address");
        validationService.assertNotNullOrBlank(personDto.getPhoneNumber(), "Phone number");
        if (personDto.getAddress() == null || personDto.getAddress().toString().equals("")) {
            throw new IllegalArgumentException("Provide an address please!");
        }
    }
}
