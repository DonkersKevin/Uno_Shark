package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.parking.divisionDto.CreateDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.SingleDivisionDto;
import be.switchfully.uno_shark.services.DivisionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("divisions")
public class DivisionController {

    private DivisionService service;

    public DivisionController(DivisionService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('CREATE_DIVISION')")
    public void createNewDivision(@RequestBody CreateDivisionDto createDivisionDto){
        service.createDivision(createDivisionDto);
    }

    @GetMapping(produces =  "application/json")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('GET_ALL_DIVISIONS')")
    public List<ShowDivisionDto> getAllDivisions(){
        return service.getAllDivisions();
    }

    @GetMapping(produces = "application/json", path = "/{id}")
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('GET_DIVISION')")
    public SingleDivisionDto getSingleDivision(@PathVariable long id){
        return service.getSingleDivision(id);
    }


}
