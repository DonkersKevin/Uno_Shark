package be.switchfully.uno_shark.services.util;

import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public void checkRequiredFields(CreateUserDto createUserDto) {
        if (createUserDto.getLicensePlate() == null || createUserDto.getLicensePlate().equals("")) {
            throw new IllegalArgumentException("Provide an license plate please!");
        }
    }
}
