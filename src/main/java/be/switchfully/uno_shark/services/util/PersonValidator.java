package be.switchfully.uno_shark.services.util;

import be.switchfully.uno_shark.services.CreatePersonDto;
import be.switchfully.uno_shark.services.GeneralValidationService;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {

    private final GeneralValidationService validationService;

    public PersonValidator(GeneralValidationService validationService) {
        this.validationService = validationService;
    }

    public void isValidEmail(String emailAddress) {
        if (!emailAddress.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email address is not valid");
        }
    }

    public void checkRequiredFields(CreatePersonDto createPersonDto) {
        validationService.assertNotNullOrBlank(createPersonDto.getEmailAddress(), "Email address");
        validationService.assertNotNullOrBlank(createPersonDto.getLandLinePhone().toString(), "Phone number");
        if (createPersonDto.getAddress() == null || createPersonDto.getAddress().toString().equals("")) {
            throw new IllegalArgumentException("Provide an address please!");
        }
    }
}
