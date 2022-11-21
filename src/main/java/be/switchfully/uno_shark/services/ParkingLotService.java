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
import be.switchfully.uno_shark.repositories.ParkingLotRepository;

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

    private final AddressService addressService;
    private final PersonService personService;
    private final DivisionService divisionService;


    public ParkingLotService(ParkingLotMapper parkingLotMapper, ParkingLotRepository parkingLotRepository, AddressService addressService, PersonService personService, DivisionService divisionService) {
        this.parkingLotMapper = parkingLotMapper;
        this.parkingLotRepository = parkingLotRepository;
        this.addressService = addressService;
        this.personService = personService;
        this.divisionService = divisionService;
    }

    public ParkingLotDto addParkingLot(CreateParkingLotDto createParkingLotDto) {
        ParkingLot checkedParkingLot = checkParkingLotForDuplicatesByFields(parkingLotMapper.CreateDtoToParkingLot(createParkingLotDto));
        log.info("Saving parking lot");
        ParkingLot returnedParkingLot = parkingLotRepository.save(checkedParkingLot);
        return parkingLotMapper.parkingLotToDto(returnedParkingLot);
    }

    private ParkingLot checkParkingLotForDuplicatesByFields(ParkingLot parkingLotToSave) {
        Address checkedAddress = addressService.checkAddressForDuplicatesByFields(parkingLotToSave.getAddress());
        parkingLotToSave.setAddress(checkedAddress);

        Person checkedPerson = personService.checkPersonForDuplicatesByFields(parkingLotToSave.getPerson());
        parkingLotToSave.setPerson(checkedPerson);

        Division checkedDivision = divisionService.checkDivisionForDuplicatesByFields(parkingLotToSave.getDivision());
        parkingLotToSave.setDivision(checkedDivision);

        return checkParkingLotItself(parkingLotToSave);
    }

    private ParkingLot checkParkingLotItself(ParkingLot parkingLot) {
        Example<ParkingLot> example = Example.of(parkingLot);
        Optional<ParkingLot> searchResult = parkingLotRepository.findOne(example);
        return searchResult.orElse(parkingLot);
    }

    public List<ParkingLotSimpleDto> getAllParkingLots() {
        return parkingLotMapper.parkingLotListToDto(parkingLotRepository.findAll());
    }

    public ParkingLotDto getParkingLotById(String id) {
        return parkingLotMapper.parkingLotToDto(parkingLotRepository.findById(Long.valueOf(id)).orElseThrow(()
                -> new NoParkingLotByThatIdException("No parkinglot with id: " + id)));
    }
}
