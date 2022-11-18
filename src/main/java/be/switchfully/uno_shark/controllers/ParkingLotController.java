package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.services.ParkingLotService;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.CreateParkingLotDto;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.ParkingLotDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("parkinglots")
public class ParkingLotController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @PreAuthorize("hasAuthority('CREATE_PARKINGLOT')")
    public ParkingLotDto createParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {

        return parkingLotService.addParkingLot(createParkingLotDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('GET_ALL_PARKINGLOTS')")
    public List<ParkingLotDto> getAllParkinglots(){
        log.info("Getting all parkinglots");
        return parkingLotService.getAllParkinglots();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @PreAuthorize("hasAuthority('GET_PARKINGLOT')")
    public ParkingLotDto getParkingLotById(@PathVariable String id){
        log.info("Looking for parkinglot with id: " + id);
        return parkingLotService.getParkingLotById(id);
    }
}
