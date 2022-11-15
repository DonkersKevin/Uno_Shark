package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.parking.dto.CreateDivisionDto;
import be.switchfully.uno_shark.services.DivisionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("divisions")
public class DivisionController {

    private DivisionService service;

    public DivisionController(DivisionService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(CREATED)
    public void createNewDivision(@RequestBody CreateDivisionDto createDivisionDto){
        service.createDivision(createDivisionDto);
    }

}
