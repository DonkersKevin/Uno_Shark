package be.switchfully.uno_shark.domain.parking.dto;

import be.switchfully.uno_shark.domain.parking.Parkinglot;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {
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
                createParkingLotDto.getContactPerson(),
                createParkingLotDto.getAddress(),
                createParkingLotDto.getPricePerHour()
        );
    }

    public ParkingLotDto ParkingLotToDto(Parkinglot returnedParkinglot) {
        return new ParkingLotDto(
                returnedParkinglot.getId(),
                returnedParkinglot.getName(),
                returnedParkinglot.getParkingCategory(),
                returnedParkinglot.getDivision(),
                returnedParkinglot.getCapacity(),
                returnedParkinglot.getContactPerson(),
                returnedParkinglot.getAddress(),
                returnedParkinglot.getPricePerHour()
        );
    }
}
