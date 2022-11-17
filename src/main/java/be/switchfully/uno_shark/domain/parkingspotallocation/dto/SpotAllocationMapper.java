package be.switchfully.uno_shark.domain.parkingspotallocation.dto;

import be.switchfully.uno_shark.domain.parkingspotallocation.ParkingSpotAllocation;
import be.switchfully.uno_shark.repositories.ParkingLotRepository;
import be.switchfully.uno_shark.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class SpotAllocationMapper {

   private final UserRepository userRepository;

   private final ParkingLotRepository parkingLotRepository;

    public SpotAllocationMapper(UserRepository userRepository, ParkingLotRepository parkingLotRepository) {
        this.userRepository = userRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingSpotAllocation mapDtoToSpotAllocation(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        ParkingSpotAllocation parkingSpotAllocation = new ParkingSpotAllocation(
                userRepository.findById(createParkingSpotAllocationDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("No such user id exists!")),
                createParkingSpotAllocationDto.getLicensePlate(),
                parkingLotRepository.findById(createParkingSpotAllocationDto.getParkingLotId()).orElseThrow()
        );
        return parkingSpotAllocation;
    }
}
