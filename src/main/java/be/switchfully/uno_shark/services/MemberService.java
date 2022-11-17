package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.User;
import be.switchfully.uno_shark.domain.person.dto.*;
import be.switchfully.uno_shark.repositories.UserRepository;
import be.switchfully.uno_shark.services.util.PersonValidator;
import be.switchfully.uno_shark.services.util.UserValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PersonValidator personValidator;

    private final UserValidator userValidator;

    public MemberService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
    }

    public UserDto createMember(CreateUserDto createUserDto) {
        PersonDto personDto = userMapper.mapUserDtoToPersonDto(createUserDto);
        personValidator.checkRequiredFields(personDto);
        userValidator.checkRequiredFields(createUserDto);
        personValidator.isValidEmail(createUserDto.getEmailAddress());
        isUniqueEmail(createUserDto.getEmailAddress());
        isUniqueLicensePlate(createUserDto.getLicensePlate());
        User user = userRepository.save(userMapper.mapUserDtoToUser(createUserDto));
        return userMapper.mapUserToUserDto(user);
    }

    private void isUniqueLicensePlate(LicensePlate licensePlate) {
        if(userRepository.findUserByLicensePlate_LicensePlateNumber(licensePlate.getLicensePlateNumber()) != null) {
            throw new IllegalArgumentException("This license plate is already registered.");
        }
    }

    private void isUniqueEmail(String emailAddress) {
        if(userRepository.findUserByPerson_EmailAddress(emailAddress) != null){
            throw new IllegalArgumentException("This email address is already registered.");
        }
    }
    
    public List<UserDtoLimitedInfo> getAllMembers() {
        List<User> userList = userRepository.findAll();
        List<User> memberList = userList.stream().filter(user -> user.getRole.equals("manager")).collect(Collectors.toList());
        return userMapper.mapListUserToUserDtoLimitedInfo(memberList);
    }
}
