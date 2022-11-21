package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.parking.ParkingCategory;
import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.parking.divisionDto.DivisionMapper;
import be.switchfully.uno_shark.domain.person.dto.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingLotMapper {
    Logger log = LoggerFactory.getLogger(getClass());
    private final DivisionMapper divisionMapper;
    private final PersonMapper personMapper;
    private ParkingLotMapper(DivisionMapper divisionMapper, PersonMapper personMapper) {
        this.divisionMapper = divisionMapper;
        this.personMapper = personMapper;
    }

    public ParkingLot CreateDtoToParkingLot(CreateParkingLotDto createParkingLotDto) {
        log.info("Converting Dto -> Parkinglot");
        return new ParkingLot(
                createParkingLotDto.getName(),
                ParkingCategory.valueOf(createParkingLotDto.getParkingCategory().toUpperCase()),
                divisionMapper.mapToDivision(createParkingLotDto.getDivision()),
                createParkingLotDto.getCapacity(),
                personMapper.dtoToPerson(createParkingLotDto.getPerson()),
                createParkingLotDto.getAddress(),
                createParkingLotDto.getPricePerHour()
        );
    }

    public ParkingLotDto parkingLotToDto(ParkingLot parkinglot) {
        log.info("Converting Dto <- Parkinglot");
        return new ParkingLotDto(
                parkinglot.getId(),
                parkinglot.getName(),
                parkinglot.getParkingCategory(),
                divisionMapper.mapSingleDivisionDto(parkinglot.getDivision()),
                parkinglot.getCapacity(),
                parkinglot.getPerson(),
                parkinglot.getAddress(),
                parkinglot.getPricePerHour()
        );
    }

    public ParkingLotSimpleDto parkingLotToSimpleDto(ParkingLot parkingLot){
        return new ParkingLotSimpleDto(
                parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCapacity(),
                parkingLot.getMobilePhone(),
                parkingLot.getLandLinePhone()
        );
    }

    public List<ParkingLotSimpleDto> parkingLotListToDto(List<ParkingLot> parkingLots) {
        log.info("Converting List : ParkingLotSimpleDto <- Parkinglot");
        return parkingLots.stream().map(this::parkingLotToSimpleDto).toList();
    }
}
