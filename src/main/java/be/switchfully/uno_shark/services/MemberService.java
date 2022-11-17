package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.person.Role;
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

    public MemberService(UserRepository userRepository, PersonValidator personValidator, UserValidator userValidator, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.personValidator = personValidator;
        this.userValidator = userValidator;

    }

    public UserDto createMember(CreateUserDto createUserDto) {
        CreatePersonDto createPersonDto = userMapper.mapUserDtoToPersonDto(createUserDto);
        personValidator.checkRequiredFields(createPersonDto);
        userValidator.checkRequiredFields(createUserDto);
        personValidator.isValidEmail(createUserDto.getEmailAddress());
        User user = userRepository.save(userMapper.mapCreateUserDtoToUser(createUserDto));
        return userMapper.mapUserToUserDto(user);
    }

    public UserDto findAMember(long id) {
        return userMapper.mapUserToUserDto(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such user exists!")));
    }


    public List<UserDtoLimitedInfo> getAllMembers() {
        List<User> userList = userRepository.findAll();
        List<User> memberList = userList.stream().filter(user -> user.getRole() == Role.MEMBER).collect(Collectors.toList());
        return userMapper.mapListUserToUserDtoLimitedInfo(memberList);
    }

}
