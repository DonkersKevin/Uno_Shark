package be.switchfully.uno_shark.services.util;

import be.switchfully.uno_shark.domain.parking.parkingLotDto.CreateParkingLotDto;
import be.switchfully.uno_shark.services.GeneralValidationService;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotDtoValidator {

    private final GeneralValidationService validationService;

    public ParkingLotDtoValidator(GeneralValidationService validationService) {
        this.validationService = validationService;
    }

    public void checkParkingLotDtoForNullFields(CreateParkingLotDto createParkingLotDto) {
        validationService.assertNotNullOrBlank(createParkingLotDto.getParkingCategory(), "parking category");
        validationService.assertNotNullOrBlank(createParkingLotDto.getName(), "parking name");

        //Check capacity address and price
        if (createParkingLotDto.getCapacity() < 1){
            throw new IllegalArgumentException("Parking capacity is too low.");
        }

        if (createParkingLotDto.getAddress() == null){
            throw new IllegalArgumentException("Please provide an address.");
        }

        if (createParkingLotDto.getPricePerHour().getAmount() <= 0){
            throw new IllegalArgumentException("Please provide a valid price.");
        }

        if (createParkingLotDto.getPricePerHour().getCurrency() == null){
            throw new IllegalArgumentException("Please provide a valid currency.");
        }
    }
}
