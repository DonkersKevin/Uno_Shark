package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.controllers.exceptions.NoSpotsLeftException;
import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.parkingspotallocation.ParkingSpotAllocation;
import be.switchfully.uno_shark.domain.parkingspotallocation.dto.CreateParkingSpotAllocationDto;
import be.switchfully.uno_shark.domain.parkingspotallocation.dto.SpotAllocationMapper;
import be.switchfully.uno_shark.domain.person.MembershipLevel;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.repositories.LicensePlateRepository;
import be.switchfully.uno_shark.repositories.ParkingLotRepository;
import be.switchfully.uno_shark.repositories.SpotAllocationRepository;
import be.switchfully.uno_shark.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SpotAllocationService {

    private final SpotAllocationMapper spotAllocationMapper;
    private final UserRepository userRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final LicensePlateRepository licensePlateRepository;
    private SpotAllocationRepository spotAllocationRepository;

    public SpotAllocationService(SpotAllocationMapper spotAllocationMapper, UserRepository userRepository, ParkingLotRepository parkingLotRepository, LicensePlateRepository licensePlateRepository, SpotAllocationRepository spotAllocationRepository) {
        this.spotAllocationMapper = spotAllocationMapper;
        this.userRepository = userRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.licensePlateRepository = licensePlateRepository;
        this.spotAllocationRepository = spotAllocationRepository;
    }

    public Long allocateParkingSpot(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        verifyUser(createParkingSpotAllocationDto);
        verifyLicensePlate(createParkingSpotAllocationDto);
        verifyParkingLot(createParkingSpotAllocationDto);
        verifyParkingLotCapacity(createParkingSpotAllocationDto);

        ParkingSpotAllocation parkingSpotAllocationToSave = spotAllocationMapper.mapDtoToSpotAllocation(createParkingSpotAllocationDto);

        ParkingSpotAllocation parkingSpotAllocation = spotAllocationRepository.save(parkingSpotAllocationToSave);

        return parkingSpotAllocation.getId();
    }


    private void verifyUser(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        Long userId = createParkingSpotAllocationDto.getUserId();
        if (userRepository.findById(userId).isEmpty())
            throw new IllegalArgumentException("Unknown userID.");
    }

    private void verifyLicensePlate(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        LicensePlate givenLicensePlate = createParkingSpotAllocationDto.getLicensePlate();
        Long userId = createParkingSpotAllocationDto.getUserId();

        LicensePlate extractedLicensePlate = userRepository.findById(userId).orElseThrow().getLicensePlate();

        MembershipLevel membershipLevel = userRepository.findById(userId).orElseThrow().getMemberLevel();

        if (!givenLicensePlate.equals(extractedLicensePlate) && membershipLevel != MembershipLevel.GOLD) {
            throw new IllegalArgumentException("provided license plate is not the same as member's license plate.");
        }
    }

    private void verifyParkingLot(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        Long parkingLotId = createParkingSpotAllocationDto.getParkingLotId();
        if (parkingLotRepository.findById(parkingLotId).isEmpty()) {
            throw new IllegalArgumentException("Unknown parking lot.");
        }
    }

    private void verifyParkingLotCapacity(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        Long parkingLotId = createParkingSpotAllocationDto.getParkingLotId();
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow();

        int capacity = parkingLot.getCapacity();

        int usage = (int) spotAllocationRepository.findAll().stream().filter(parkingSpotAllocation -> parkingSpotAllocation.getParkinglot().getId() == parkingLotId).count();

        if (usage == capacity) {
            throw new NoSpotsLeftException("parking is full!");
        }
    }

}
