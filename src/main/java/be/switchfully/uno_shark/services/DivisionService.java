package be.switchfully.uno_shark.services;

import be.switchfully.uno_shark.domain.parking.Division;
import be.switchfully.uno_shark.domain.parking.divisionDto.CreateDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.DivisionMapper;
import be.switchfully.uno_shark.domain.parking.divisionDto.SingleDivisionDto;
import be.switchfully.uno_shark.repositories.DivisionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DivisionService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    DivisionRepository divisionRepository;
    DivisionMapper divisionMapper;

    GeneralValidationService generalValidationService;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper, GeneralValidationService generalValidationService) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
        this.generalValidationService = generalValidationService;
    }

    public Division checkDivisionForDuplicatesByFields(Division division) {
        log.info("Checking database for existing Division based on fields.");
        Example<Division> example = Example.of(division);
        Optional<Division> searchResult = divisionRepository.findOne(example);
        return searchResult.orElse(division);
    }

    public void createDivision(CreateDivisionDto createDivisionDto) {
        generalValidationService.assertNotNullOrBlank(createDivisionDto.getName(), "Name");
        generalValidationService.assertNotNullOrBlank(createDivisionDto.getOriginalName(), "Original name");
        generalValidationService.assertNotNullOrBlank(createDivisionDto.getDirector(), "Director ");
        divisionRepository.save(divisionMapper.mapToDivision(createDivisionDto));
    }

    public List<ShowDivisionDto> getAllDivisions() {
        return divisionMapper.mapToDto(divisionRepository.findAll());
    }

    public SingleDivisionDto getSingleDivision(long id) {
        return divisionMapper.mapSingleDivisionDto(divisionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such division exists!")));
    }
}
