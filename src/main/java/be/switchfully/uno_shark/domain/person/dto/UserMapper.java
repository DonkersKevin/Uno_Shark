package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.parking.Division;
import be.switchfully.uno_shark.domain.parking.dto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.person.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {


    public User mapUserDtoToUser(CreateUserDto createUserDto) {
        return new User(
                createUserDto.getFirstName(),
                createUserDto.getLastName(),
                createUserDto.getAddress(),
                createUserDto.getMobileNumber(),
                createUserDto.getPhoneNumber(),
                createUserDto.getEmailAddress(),
                createUserDto.getLicensePlate());
    }

    public UserDto mapUserToUserDto(User user) {
        return new UserDto()
                .setFirstName(user.getPerson().getFirstName())
                .setLastName(user.getPerson().getLastName())
                .setAddress(user.getPerson().getAddress())
                .setMobileNumber(user.getPerson().getMobileNumber())
                .setPhoneNumber(user.getPerson().getPhoneNumber())
                .setEmailAddress(user.getPerson().getEmailAddress())
                .setLicensePlate(user.getLicensePlate())
                .setRegistrationDate(user.getRegistrationDate());
    }

    public PersonDto mapUserDtoToPersonDto(CreateUserDto createUserDto) {
        return new PersonDto()
                .setFirstName(createUserDto.getFirstName())
                .setLastName(createUserDto.getLastName())
                .setAddress(createUserDto.getAddress())
                .setPhoneNumber(createUserDto.getPhoneNumber())
                .setMobileNumber(createUserDto.getMobileNumber())
                .setEmailAddress(createUserDto.getEmailAddress());
    }


    public List<UserDtoLimitedInfo> mapListUserToUserDtoLimitedInfo(List<User> users) {
        return users.stream().map(this::mapUserToUserDtoLimitedInfo).toList();
    }

    private UserDtoLimitedInfo mapUserToUserDtoLimitedInfo(User user) {
        return new UserDtoLimitedInfo(user.getPerson().getId(),user.getPerson().getFirstName(), user.getPerson().getLastName(), user.getPerson().getPhoneNumber(), user.getPerson().getEmailAddress(), user.getLicensePlate().getLicensePlateNumber(), user.getRegistrationDate());
    }
}
