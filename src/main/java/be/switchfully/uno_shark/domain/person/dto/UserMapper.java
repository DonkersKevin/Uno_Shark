package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.User;
import be.switchfully.uno_shark.services.CreatePersonDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {


    public User mapCreateUserDtoToUser(CreateUserDto createUserDto) {
        User user = new User(
                createUserDto.getFirstName(),
                createUserDto.getLastName(),
                createUserDto.getAddress(),
                createUserDto.getPhoneNumber(),
                createUserDto.getMobileNumber(),
                createUserDto.getEmailAddress(),
                createUserDto.getLicensePlate());
        changeLevelMaybe(user, createUserDto);
        return user;
    }


    private void changeLevelMaybe(User user, CreateUserDto dto) {
        if (dto.getMemberLevel() == null) return;
        user.setMemberLevel(dto.getMemberLevel());
    }

    public UserDto mapUserToUserDto(User user) {
        return new UserDto()
                .setId(user.getPerson().getId())
                .setFirstName(user.getPerson().getFirstName())
                .setLastName(user.getPerson().getLastName())
                .setAddress(user.getPerson().getAddress())
                .setMobileNumber(user.getPerson().getMobilePhone().toString())
                .setPhoneNumber(user.getPerson().getLandLinePhone().toString())
                .setEmailAddress(user.getPerson().getEmailAddress())
                .setLicensePlate(user.getLicensePlate())
                .setRegistrationDate(user.getRegistrationDate())
                .setMemberLevel(user.getMemberLevel());
    }

    public CreatePersonDto mapUserDtoToPersonDto(CreateUserDto createUserDto) {
        return new CreatePersonDto()
                .setFirstName(createUserDto.getFirstName())
                .setLastName(createUserDto.getLastName())
                .setAddress(createUserDto.getAddress())
                .setLandLinePhone(createUserDto.getPhoneNumber())
                .setMobilePhone(createUserDto.getMobileNumber())
                .setEmailAddress(createUserDto.getEmailAddress());
    }


    public List<UserDtoLimitedInfo> mapListUserToUserDtoLimitedInfo(List<User> users) {
        return users.stream().map(this::mapUserToUserDtoLimitedInfo).toList();
    }

    private UserDtoLimitedInfo mapUserToUserDtoLimitedInfo(User user) {
        return new UserDtoLimitedInfo(user.getPerson().getId(),user.getPerson().getFirstName(), user.getPerson().getLastName(), user.getPerson().getLandLinePhone().toString(), user.getPerson().getEmailAddress(), user.getLicensePlate().getLicensePlateNumber(), user.getRegistrationDate());
    }
}
