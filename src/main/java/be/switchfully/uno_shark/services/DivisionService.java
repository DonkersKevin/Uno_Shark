package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.parking.mapper.DivisionMapper;
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

    GeneralValidationService generalValidationService;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper, GeneralValidationService generalValidationService) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
        this.generalValidationService = generalValidationService;
    }

    public void createDivision(CreateDivisionDto createDivisionDto){
        generalValidationService.assertNotNullOrBlank(createDivisionDto.getName(), "Name");
        generalValidationService.assertNotNullOrBlank(createDivisionDto.getOriginalName(), "Original name");
        generalValidationService.assertNotNullOrBlank(createDivisionDto.getDirector(), "Director ");
        divisionRepository.save(divisionMapper.mapToDivision(createDivisionDto));
    }

    public List<ShowDivisionDto> getAllDivisions(){
         return divisionMapper.mapToDto(divisionRepository.findAll());
    }

    public SingleDivisionDto getSingleDivision(long id){
        return  divisionMapper.mapSingleDivisionDto(divisionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such division exists!")));
    }
}
