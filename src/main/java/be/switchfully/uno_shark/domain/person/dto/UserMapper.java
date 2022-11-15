package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.User;

public class UserMapper {


    public User mapUserDtoToUser(CreateUserDto createUserDto) {
        return new User()
                .setFirstName(createUserDto.getFirstName())
                .setLastName(createUserDto.getLastName())
                .setAddress(createUserDto.getAddress())
                .setMobileNumber(createUserDto.getMobileNumber())
                .setPhoneNumber(createUserDto.getPhoneNumber())
                .setEmailAddress(createUserDto.getEmailAddress())
                .setLicensePlate(createUserDto.getLicensePlate())
                .setRegistrationDate(createUserDto.getRegistrationDate());
    }

    public UserDto mapUserToUserDto(User user) {
        return new UserDto()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setAddress(user.getAddress())
                .setMobileNumber(user.getMobileNumber())
                .setPhoneNumber(user.getPhoneNumber())
                .setEmailAddress(user.getEmailAddress())
                .setLicensePlate(user.getLicensePlate())
                .setRegistrationDate(user.getRegistrationDate());
    }
}