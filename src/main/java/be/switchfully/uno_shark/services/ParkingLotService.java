package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.CreateParkingLotDto;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.ParkingLotDto;
import be.switchfully.uno_shark.domain.parking.parkingLotDto.ParkingLotMapper;
import be.switchfully.uno_shark.repositories.ParkingLotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ParkingLotService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ParkingLotMapper parkingLotMapper;
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotMapper parkingLotMapper, ParkingLotRepository parkingLotRepository) {
        this.parkingLotMapper = parkingLotMapper;
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLotDto addParkingLot(CreateParkingLotDto createParkingLotDto) {
        ParkingLot parkingLotTosave = parkingLotMapper.CreateDtoToParkingLot(createParkingLotDto);
        ParkingLot returnedParkinglot = parkingLotRepository.save(parkingLotTosave);
        return parkingLotMapper.parkingLotToDto(returnedParkinglot);
    }

    public List<ParkingLotDto> getAllParkinglots() {
        log.info("Service getting all parkinglots");
        return parkingLotMapper.parkingLotListToDto(parkingLotRepository.findAll());
    }

    public ParkingLotDto getParkingLotById(String id) {
        return parkingLotMapper.parkingLotToDto(parkingLotRepository.findById(Long.valueOf(id)).orElseThrow(()
                -> new NoSuchElementException("No parkinglot with id: " + id + " is not found.")));
    }
}
