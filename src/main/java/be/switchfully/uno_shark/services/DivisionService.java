package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.parking.divisionDto.CreateDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.DivisionMapper;
import be.switchfully.uno_shark.repositories.DivisionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DivisionService {
    DivisionRepository divisionRepository;
    DivisionMapper divisionMapper;

    DivisionValidationService divisionValidationService;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper, DivisionValidationService divisionValidationService) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
        this.divisionValidationService = divisionValidationService;
    }

    public void createDivision(CreateDivisionDto createDivisionDto){
        divisionValidationService.assertNotNullOrBlank(createDivisionDto.getName(), "Name");
        divisionValidationService.assertNotNullOrBlank(createDivisionDto.getOriginalName(), "Original name");
        divisionValidationService.assertNotNullOrBlank(createDivisionDto.getDirector(), "Director ");
        divisionRepository.save(divisionMapper.mapToDivision(createDivisionDto));
    }

    public List<ShowDivisionDto> getAllDivisions(){
         return divisionMapper.mapToDto(divisionRepository.findAll());
    }
}
