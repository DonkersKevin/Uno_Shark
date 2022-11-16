package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.parking.dto.CreateDivisionDto;
import be.switchfully.uno_shark.domain.parking.dto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.parking.dto.SingleDivisionDto;
import be.switchfully.uno_shark.domain.parking.mapper.DivisionMapper;
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

    public SingleDivisionDto getSingleDivision(long id){
        return  divisionMapper.mapSingleDivisionDto(divisionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such division exists!")));
    }
}
