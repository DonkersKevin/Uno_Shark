package be.switchfully.uno_shark.services.util;

import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import be.switchfully.uno_shark.repositories.UserRepository;
import be.switchfully.uno_shark.services.GeneralValidationService;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private GeneralValidationService serviceChan;
    private UserRepository repoKun;

    public UserValidator(GeneralValidationService serviceChan, UserRepository repoKun) {
        this.serviceChan = serviceChan;
        this.repoKun = repoKun;
    }

    public void checkRequiredFields(CreateUserDto createUserDto) {
        if (createUserDto.getLicensePlate() == null) {
            throw new IllegalArgumentException("Provide an license plate please!");
        }

        serviceChan.assertNotNullOrBlank(createUserDto.getLicensePlate().getLicensePlateNumber(),"plate number");
        isUniqueLicensePlate(createUserDto.getLicensePlate());

        serviceChan.assertNotNullOrBlank(createUserDto.getUserName(), "username");
//        isUniqueUsername(createUserDto.getUserName());
    }

    private void isUniqueLicensePlate(LicensePlate licensePlate) {
        if(repoKun.findUserByLicensePlate_LicensePlateNumber(licensePlate.getLicensePlateNumber()) != null) {
            throw new IllegalArgumentException("This license plate is already registered!");
        }
    }

//    private void isUniqueUsername(String username) {
//        if(repoKun.findUserByUsername(username) != null) {
//            throw new IllegalArgumentException("This username is already registered!");
//        }
//    }
}
