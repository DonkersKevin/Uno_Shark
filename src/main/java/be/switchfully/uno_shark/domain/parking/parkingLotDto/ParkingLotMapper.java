package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.parking.divisionDto.DivisionMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingLotMapper {
    private final DivisionMapper divisionMapper;
    public ParkingLotMapper(DivisionMapper divisionMapper) {
        this.divisionMapper = divisionMapper;
    }

    public ParkingLot dtoToParkingLot(CreateParkingLotDto createParkingLotDto) {
        //todo fix
        return null;
    }

    public ParkingLot CreateDtoToParkingLot(CreateParkingLotDto createParkingLotDto) {
        return new ParkingLot(
                createParkingLotDto.getName(),
                createParkingLotDto.getParkingCategory(),
                createParkingLotDto.getDivision(),
                createParkingLotDto.getCapacity(),
                createParkingLotDto.getPerson(),
                createParkingLotDto.getAddress(),
                createParkingLotDto.getPricePerHour()
        );
    }

    public ParkingLotDto parkingLotToDto(ParkingLot parkinglot) {
        //Todo change Dtos
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

    public List<ParkingLotDto> parkingLotListToDto(List<ParkingLot> parkingLots) {
        System.out.println("running");
        return parkingLots.stream().map(this::parkingLotToDto).toList();
    }
}
