package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.controllers.exceptions.NoParkingLotByThatIdException;
import be.switchfully.uno_shark.domain.parking.Division;
import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.CreateParkingLotDto;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.ParkingLotDto;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.ParkingLotMapper;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.ParkingLotSimpleDto;
import be.switchfully.uno_shark.domain.person.Person;
import be.switchfully.uno_shark.domain.person.address.Address;
import be.switchfully.uno_shark.repositories.AddressRepository;
import be.switchfully.uno_shark.repositories.DivisionRepository;
import be.switchfully.uno_shark.repositories.ParkingLotRepository;
import be.switchfully.uno_shark.repositories.PersonRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParkingLotService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ParkingLotMapper parkingLotMapper;
    private final ParkingLotRepository parkingLotRepository;
    private final DivisionRepository divisionRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public ParkingLotService(ParkingLotMapper parkingLotMapper, ParkingLotRepository parkingLotRepository, DivisionRepository divisionRepository, PersonRepository personRepository, AddressRepository addressRepository) {
        this.parkingLotMapper = parkingLotMapper;
        this.parkingLotRepository = parkingLotRepository;
        this.divisionRepository = divisionRepository;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    public ParkingLotDto addParkingLot(CreateParkingLotDto createParkingLotDto) {
        //Todo fieldvalidation for createParkingLotDto
        ParkingLot parkingLotTosave = parkingLotMapper.CreateDtoToParkingLot(createParkingLotDto);

        //Prepping children to check
        Address addressToCheck = parkingLotTosave.getAddress();
        Address checkedAddress = getAddressByFieldQuery(addressToCheck);
        parkingLotTosave.setAddress(checkedAddress);

        /*
        Person personToCheck = parkingLotTosave.getPerson();
        Division divisionToCheck = parkingLotTosave.getDivision();
         */


        log.info("Saving parkinglot");
        ParkingLot returnedParkinglot = parkingLotRepository.save(parkingLotTosave);
        return parkingLotMapper.parkingLotToDto(returnedParkinglot);
    }

    public Address getAddressByFieldQuery(Address address){
        log.info("Checking database for existing address based on fields.");
        Example<Address> example = Example.of(address);
        Optional<Address> searchresult = addressRepository.findOne(example);
        return searchresult.orElse(address);

        /*
                if(searchresult.isPresent()){
            return searchresult.get();
        }else return address;
         */
    }

    public List<ParkingLotSimpleDto> getAllParkinglots() {
        return parkingLotMapper.parkingLotListToDto(parkingLotRepository.findAll());
    }

    public ParkingLotDto getParkingLotById(String id) {
        return parkingLotMapper.parkingLotToDto(parkingLotRepository.findById(Long.valueOf(id)).orElseThrow(()
                -> new NoParkingLotByThatIdException("No parkinglot with id: " + id)));
    }

    //Todo temp, pls move or remove
    public List<Address> getAllAddresses() {
        return addressRepository.findAll().stream().toList();
    }
}
