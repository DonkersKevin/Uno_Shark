package be.switchfully.uno_shark.controllers;


import be.switchfully.uno_shark.domain.parkingspotallocation.dto.CreateParkingSpotAllocationDto;
import be.switchfully.uno_shark.services.SpotAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spotallocation")
public class ParkingSpotAllocationController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SpotAllocationService spotAllocationService;


    public ParkingSpotAllocationController(SpotAllocationService spotAllocationService) {
        this.spotAllocationService = spotAllocationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createParkingSpotAllocation(@RequestBody CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        log.info("adding the following ParkingSpotAllocation: "+ createParkingSpotAllocationDto);
        return spotAllocationService.allocateParkingSpot(createParkingSpotAllocationDto);
    }

}
