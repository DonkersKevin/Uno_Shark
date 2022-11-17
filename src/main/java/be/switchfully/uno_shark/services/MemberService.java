package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.person.LicensePlate;
import be.switchfully.uno_shark.domain.person.User;
import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import be.switchfully.uno_shark.domain.person.dto.PersonDto;
import be.switchfully.uno_shark.domain.person.dto.UserDto;
import be.switchfully.uno_shark.domain.person.dto.UserMapper;
import be.switchfully.uno_shark.repositories.UserRepository;
import be.switchfully.uno_shark.security.KeycloakService;
import be.switchfully.uno_shark.security.KeycloakUserDTO;
import be.switchfully.uno_shark.services.util.PersonValidator;
import be.switchfully.uno_shark.services.util.UserValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PersonValidator personValidator;

    private final UserValidator userValidator;

    private final KeycloakService keycloakService;

    public MemberService(UserRepository userRepository, PersonValidator personValidator, UserValidator userValidator, UserMapper userMapper, KeycloakService keycloakService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.personValidator = personValidator;
        this.userValidator = userValidator;
        this.keycloakService = keycloakService;
    }

    public UserDto createMember(CreateUserDto createUserDto) {
        CreatePersonDto createPersonDto = userMapper.mapUserDtoToPersonDto(createUserDto);
        personValidator.checkRequiredFields(createPersonDto);
        userValidator.checkRequiredFields(createUserDto);
        personValidator.isValidEmail(createUserDto.getEmailAddress());
        User user = userRepository.save(userMapper.mapUserDtoToUser(createUserDto));
        keycloakService.addUser(new KeycloakUserDTO(user.getUsername(), createUserDto.getPassword(), user.getRole()));
        return userMapper.mapUserToUserDto(user);
    }
}
