package be.switchfully.uno_shark.controllers;


import be.switchfully.uno_shark.services.SpotAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("spotallocation")
public class ParkingSpotAllocationController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    //private final SpotAllocationService spotAllocationService;

    /**
    public ParkingSpotAllocationController(SpotAllocationService spotAllocationService) {
        this.spotAllocationService = spotAllocationService;
    }
     */
}
