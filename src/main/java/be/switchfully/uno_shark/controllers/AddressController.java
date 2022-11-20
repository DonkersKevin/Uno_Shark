package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.services.ParkingLotService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final ParkingLotService parkingLotService;

    public AddressController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @GetMapping(path = "byfield" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Address getAddressByFieldQuery(@RequestBody Address address){
        return parkingLotService.getAddressByFieldQuery(address);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Address> getAllAddress(){
        return parkingLotService.getAllAddresses();
    }

}
