package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.parking.ParkingLot;
import be.switchfully.uno_shark.domain.parkingspotallocation.ParkingSpotAllocation;
import be.switchfully.uno_shark.domain.parkingspotallocation.dto.CreateParkingSpotAllocationDto;
import be.switchfully.uno_shark.domain.parkingspotallocation.dto.ShowAllocationDto;
import be.switchfully.uno_shark.domain.parkingspotallocation.dto.SpotAllocationMapper;
import be.switchfully.uno_shark.domain.person.MembershipLevel;
import be.switchfully.uno_shark.domain.person.licenseplate.LicensePlate;
import be.switchfully.uno_shark.repositories.ParkingLotRepository;
import be.switchfully.uno_shark.repositories.SpotAllocationRepository;
import be.switchfully.uno_shark.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class SpotAllocationService {

    private final SpotAllocationMapper spotAllocationMapper;

    private final UserRepository userRepository;
    private final ParkingLotRepository parkingLotRepository;

    private SpotAllocationRepository spotAllocationRepository;

    public SpotAllocationService(SpotAllocationMapper spotAllocationMapper, UserRepository userRepository, ParkingLotRepository parkingLotRepository, SpotAllocationRepository spotAllocationRepository) {
        this.spotAllocationMapper = spotAllocationMapper;
        this.userRepository = userRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.spotAllocationRepository = spotAllocationRepository;
    }

    private static boolean notActive(ParkingSpotAllocation allocation) {
        return !allocation.isActive();
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
        if (userRepository.findById(userId) == null)
            throw new IllegalArgumentException("Unknown userID.");
    }

    private void verifyLicensePlate(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        LicensePlate givenLicensePlate = createParkingSpotAllocationDto.getLicensePlate();

        Long userId = createParkingSpotAllocationDto.getUserId();

        LicensePlate extractedLicensePlate = userRepository.findById(userId).orElseThrow().getLicensePlate();

        MembershipLevel membershipLevel = userRepository.findById(userId).orElseThrow().getMemberLevel();

        if (givenLicensePlate != extractedLicensePlate && membershipLevel != MembershipLevel.GOLD) {
            throw new IllegalArgumentException("provided license plate is not the same as member's license plate.");
        }
    }

    private void verifyParkingLot(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        Long parkingLotId = createParkingSpotAllocationDto.getParkingLotId();
        if (parkingLotRepository.findById(parkingLotId) == null) {
            throw new IllegalArgumentException("Unknown parking lot.");
        }
    }

    private void verifyParkingLotCapacity(CreateParkingSpotAllocationDto createParkingSpotAllocationDto) {
        Long parkingLotId = createParkingSpotAllocationDto.getParkingLotId();
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow();

        int capacity = parkingLot.getCapacity();

        int usage = (int) spotAllocationRepository.findAll().stream().filter(parkingSpotAllocation -> parkingSpotAllocation.getParkinglot().getId() == parkingLotId).count();

        if (usage == capacity) {
            throw new RuntimeException("parking is full!");
        }
    }

    public List<ShowAllocationDto> getAllAllocations(String sort, String status, Integer limit){
        List<ShowAllocationDto> dtoList = getFromRepo(status).stream()
                .map(spotAllocationMapper::mapAllocationToShowDto)
                .sorted(ShowAllocationDto::compareTo)
                .toList();
        if(sort != null && sort.equals("descending"))
            dtoList = dtoList.stream().sorted(Comparator.reverseOrder()).toList();
        if(limit != null && limit > 0 && limit <= dtoList.size())
            dtoList = dtoList.subList(0,limit);

        return dtoList;
    }

    public List<ParkingSpotAllocation> getFromRepo(String status){
        List<ParkingSpotAllocation> allocations =spotAllocationRepository.findAll();

        if("active".equals(status))
            return allocations.stream().filter(ParkingSpotAllocation::isActive).toList();
        if("stopped".equals(status))
            return allocations.stream().filter(SpotAllocationService::notActive).toList();
        return allocations;
    }



}
