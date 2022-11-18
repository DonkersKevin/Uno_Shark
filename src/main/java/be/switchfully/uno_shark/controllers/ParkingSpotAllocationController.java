package be.switchfully.uno_shark.controllers;


import be.switchfully.uno_shark.domain.parkingspotallocation.dto.CreateParkingSpotAllocationDto;
import be.switchfully.uno_shark.domain.parkingspotallocation.dto.ShowAllocationDto;
import be.switchfully.uno_shark.services.SpotAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spotallocations")
public class ParkingSpotAllocationController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SpotAllocationService spotAllocationService;


    public ParkingSpotAllocationController(SpotAllocationService spotAllocationService) {
        this.spotAllocationService = spotAllocationService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('START_ALLOCATION_PARKINGSPOT')")
    public Long createParkingSpotAllocation(@RequestBody CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        log.info("adding the following ParkingSpotAllocation: "+ createParkingSpotAllocationDto);
        return spotAllocationService.allocateParkingSpot(createParkingSpotAllocationDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('GET_ALL_PARKINGALLOCATIONS')")
    public List<ShowAllocationDto> getAll(@RequestParam(required=false) String sort,
                                          @RequestParam(required=false) String status,
                                          @RequestParam(required=false) Integer limit){
        return spotAllocationService.getAllAllocations(sort, status, limit);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('STOP_ALLOCATION_PARKINGSPOT')")
    public void stopParking(@PathVariable long id){
        spotAllocationService.stopParkingAllocation(id);
    }
}
