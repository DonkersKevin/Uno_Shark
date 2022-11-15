package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.person.User;
import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import be.switchfully.uno_shark.domain.person.dto.UserDto;
import be.switchfully.uno_shark.domain.person.dto.UserMapper;
import be.switchfully.uno_shark.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public MemberService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }

    public UserDto createMember(CreateUserDto createUserDto) {
        User user = userRepository.save(userMapper.mapUserDtoToUser(createUserDto));
        return userMapper.mapUserToUserDto(user);
    }


}
