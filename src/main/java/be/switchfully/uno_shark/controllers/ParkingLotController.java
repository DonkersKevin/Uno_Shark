package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.services.ParkingLotService;
import be.switchfully.uno_shark.domain.parking.dto.CreateParkingLotDto;
import be.switchfully.uno_shark.domain.parking.dto.ParkingLotDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parkinglots")
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ParkingLotDto createParkingLot(@RequestBody CreateParkingLotDto createParkingLotDto) {
        return parkingLotService.addParkingLot(createParkingLotDto);
    }
}
