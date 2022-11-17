package be.switchfully.uno_shark.domain.person.dto;

import be.switchfully.uno_shark.domain.person.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public User mapUserDtoToUser(CreateUserDto createUserDto) {
        User user = new User(
                createUserDto.getFirstName(),
                createUserDto.getLastName(),
                createUserDto.getAddress(),
                createUserDto.getMobileNumber(),
                createUserDto.getPhoneNumber(),
                createUserDto.getEmailAddress(),
                createUserDto.getLicensePlate());
        changeLevelMaybe(user,createUserDto);
        return user;
    }

    private void changeLevelMaybe(User user,CreateUserDto dto){
        if(dto.getMemberLevel() == null) return;

        user.setMemberLevel(dto.getMemberLevel());
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
                .setRegistrationDate(user.getRegistrationDate())
                .setMemberLevel(user.getMemberLevel());
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
}
