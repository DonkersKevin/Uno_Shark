package be.switchfully.uno_shark.domain.parkingspotallocation.dto;

import be.switchfully.uno_shark.domain.parkingspotallocation.ParkingSpotAllocation;
import be.switchfully.uno_shark.domain.person.licenseplate.IssuingCountry;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.repositories.LicensePlateRepository;
import be.switchfully.uno_shark.repositories.ParkingLotRepository;
import be.switchfully.uno_shark.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class SpotAllocationMapper {

    private final UserRepository userRepository;

    private final ParkingLotRepository parkingLotRepository;

    private final LicensePlateRepository licensePlateRepository;

    public SpotAllocationMapper(UserRepository userRepository, ParkingLotRepository parkingLotRepository, LicensePlateRepository licensePlateRepository) {
        this.userRepository = userRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.licensePlateRepository = licensePlateRepository;
    }

    public ParkingSpotAllocation mapDtoToSpotAllocation(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        ParkingSpotAllocation parkingSpotAllocation = new ParkingSpotAllocation(
                userRepository.findById(createParkingSpotAllocationDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("No such user id exists!")),
                returnExistingLicensePlate(createParkingSpotAllocationDto),
                parkingLotRepository.findById(createParkingSpotAllocationDto.getParkingLotId()).orElseThrow()
        );
        return parkingSpotAllocation;
    }

    private LicensePlate returnExistingLicensePlate(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        String licensePlateNumber = createParkingSpotAllocationDto.getLicensePlate().getLicensePlateNumber();
        IssuingCountry issuingCountry = createParkingSpotAllocationDto.getLicensePlate().getIssuingCountry();
        LicensePlate existingLicensePlate = licensePlateRepository.findLicensePlateByIssuingCountryAndLicensePlateNumber(issuingCountry, licensePlateNumber);

        return existingLicensePlate;
    }
}
