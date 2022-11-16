package be.switchfully.uno_shark.services.util;

import be.switchfully.uno_shark.domain.person.dto.PersonDto;

public class PersonValidator {

    public void isValidEmail(String emailAddress) {
        if (!emailAddress.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email address is not valid");
        }
    }

    public void checkRequiredFields(PersonDto personDto) {
        if (personDto.getEmailAddress() == null || personDto.getEmailAddress().equals("")) {
            throw new IllegalArgumentException("Provide an Email address please!");
        }
        if (personDto.getAddress() == null || personDto.getAddress().toString().equals("")) {
            throw new IllegalArgumentException("Provide an address please!");
        }
        if (personDto.getPhoneNumber() == null || personDto.getPhoneNumber().equals("")) {
            throw new IllegalArgumentException("Provide a phone number please!");
        }
    }

}
