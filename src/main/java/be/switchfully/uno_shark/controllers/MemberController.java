package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.person.dto.CreateUserDto;
import be.switchfully.uno_shark.domain.person.dto.UserDto;
import be.switchfully.uno_shark.domain.person.dto.UserDtoLimitedInfo;
import be.switchfully.uno_shark.services.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto registerMember(@RequestBody CreateUserDto createUserDto) {
        log.info("adding the following member: "+ createUserDto);
        return memberService.createMember(createUserDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('GET_ALL_MEMBERS')")
    public List<UserDtoLimitedInfo> getAllMembers() {
        log.info("Retrieving the list of all registered users");
        return memberService.getAllMembers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('GET_MEMBER')")
    public UserDto findSingleUser(@PathVariable long id){
        return memberService.findAMember(id);
    }
}
