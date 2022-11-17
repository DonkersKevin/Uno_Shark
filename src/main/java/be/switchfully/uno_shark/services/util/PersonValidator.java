package be.switchfully.uno_shark.services.util;

import be.switchfully.uno_shark.domain.person.dto.PersonDto;
import be.switchfully.uno_shark.repositories.UserRepository;
import be.switchfully.uno_shark.services.GeneralValidationService;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator {

    private GeneralValidationService validationService;
    private UserRepository userRepository;


    public PersonValidator(GeneralValidationService validationService, UserRepository userRepository) {
        this.validationService = validationService;
        this.userRepository = userRepository;
    }

    public void isValidEmail(String emailAddress) {
        if (!emailAddress.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email address is not valid");
        }

        isUniqueEmail(emailAddress);
    }

    public void checkRequiredFields(PersonDto personDto) {
        validationService.assertNotNullOrBlank(personDto.getEmailAddress(), "Email address");
        validationService.assertNotNullOrBlank(personDto.getPhoneNumber(), "Phone number");
        if (personDto.getAddress() == null || personDto.getAddress().toString().equals("")) {
            throw new IllegalArgumentException("Provide an address please!");
        }

    }

    private void isUniqueEmail(String emailAddress) {
        if(userRepository.findUserByPerson_EmailAddress(emailAddress) != null){
            throw new IllegalArgumentException("This email address is already registered.");
        }
    }
}
