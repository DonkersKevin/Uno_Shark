package be.switchfully.uno_shark.services.util;

import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;

public class PersonValidator {

    public void isValidEmail(String emailAddress) {

        if (!emailAddress.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email address is not valid");
        }

    }

    public void checkRequiredFields(CreateUserDto createUserDto) {
        if (createUserDto.getEmailAddress() == null || createUserDto.getEmailAddress().equals("")) {
            throw new IllegalArgumentException("Provide an Email address please!");
        }
        if (createUserDto.getAddress() == null || createUserDto.getAddress().equals("")) {
            throw new IllegalArgumentException("Provide an address please!");
        }
        if (createUserDto.getPhoneNumber() == null || createUserDto.getPhoneNumber().equals("")) {
            throw new IllegalArgumentException("Provide a phone number please!");
        }
    }

}
