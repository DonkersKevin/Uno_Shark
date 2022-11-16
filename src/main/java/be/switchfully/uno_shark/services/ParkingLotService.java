package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.parking.Parkinglot;
import be.switchfully.uno_shark.domain.parking.dto.CreateParkingLotDto;
import be.switchfully.uno_shark.domain.parking.dto.ParkingLotDto;
import be.switchfully.uno_shark.domain.parking.dto.ParkingLotMapper;
import be.switchfully.uno_shark.repositories.ParkingLotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    private final ParkingLotMapper parkingLotMapper;
    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotMapper parkingLotMapper, ParkingLotRepository parkingLotRepository) {
        this.parkingLotMapper = parkingLotMapper;
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLotDto addParkingLot(CreateParkingLotDto createParkingLotDto) {
        Parkinglot parkingLotTosave = parkingLotMapper.CreateDtoToParkingLot(createParkingLotDto);
        Parkinglot returnedParkinglot = parkingLotRepository.save(parkingLotTosave);
        return parkingLotMapper.ParkingLotToDto(returnedParkinglot);
    }
}
