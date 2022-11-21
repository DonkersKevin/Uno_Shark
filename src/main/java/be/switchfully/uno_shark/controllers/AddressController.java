package be.switchfully.uno_shark.controllers;

import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.services.AddressService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /*
    //Todo remove or alter so it doesnt break
    @GetMapping(path = "byfield" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Address getAddressByFieldQuery(@RequestBody Address address){
        return addressService.checkAddressForDuplicatesByFields(address);
    }
     */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Address> getAllAddress(){
        return addressService.getAllAddresses();
    }

}
