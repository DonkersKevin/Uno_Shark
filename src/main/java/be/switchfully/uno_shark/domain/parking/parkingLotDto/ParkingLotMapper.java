package be.switchfully.uno_shark.domain.parking.parkingLotDto;

import be.switchfully.uno_shark.domain.parking.Parkinglot;
import be.switchfully.uno_shark.domain.parking.divisionDto.DivisionMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingLotMapper {
    private final DivisionMapper divisionMapper;
    public ParkingLotMapper(DivisionMapper divisionMapper) {
        this.divisionMapper = divisionMapper;
    }

    public Parkinglot dtoToParkingLot(CreateParkingLotDto createParkingLotDto) {
        //todo fix
        return null;
    }

    public Parkinglot CreateDtoToParkingLot(CreateParkingLotDto createParkingLotDto) {
        return new Parkinglot(
                createParkingLotDto.getName(),
                createParkingLotDto.getParkingCategory(),
                createParkingLotDto.getDivision(),
                createParkingLotDto.getCapacity(),
                createParkingLotDto.getPerson(),
                createParkingLotDto.getAddress(),
                createParkingLotDto.getPricePerHour()
        );
    }

    public ParkingLotDto parkingLotToDto(Parkinglot parkinglot) {
        //Todo change Dtos
        return new ParkingLotDto(
                parkinglot.getId(),
                parkinglot.getName(),
                parkinglot.getParkingCategory(),
                divisionMapper.mapToSingleDto(parkinglot.getDivision()),
                parkinglot.getCapacity(),
                parkinglot.getPerson(),
                parkinglot.getAddress(),
                parkinglot.getPricePerHour()
        );
    }

    public List<ParkingLotDto> parkingLotListToDto(List<Parkinglot> parkingLots) {
        System.out.println("running");
        return parkingLots.stream().map(this::parkingLotToDto).toList();
    }
}
