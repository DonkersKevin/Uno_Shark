package be.switchfully.uno_shark.domain.parking.mapper;

import be.switchfully.uno_shark.domain.parking.Division;
import be.switchfully.uno_shark.domain.parking.dto.CreateDivisionDto;
import be.switchfully.uno_shark.repositories.DivisionRepository;
import be.switchfully.uno_shark.services.DivisionValidationService;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    private DivisionRepository divisionRepository;
    private DivisionValidationService divisionValidationService;

    public DivisionMapper(DivisionRepository divisionRepository, DivisionValidationService divisionValidationService) {
        this.divisionRepository = divisionRepository;
        this.divisionValidationService = divisionValidationService;
    }

    public Division mapToDivision(CreateDivisionDto createDivisionDto) {
        Division freshDivision = new Division(createDivisionDto.getName(), createDivisionDto.getOriginalName(), createDivisionDto.getDirector());

        if (createDivisionDto.getParentId() != 0 && !divisionRepository.existsById(createDivisionDto.getParentId())) {
            return freshDivision;
        }

        freshDivision.setParent(divisionRepository.findById(createDivisionDto.getParentId()).orElseThrow());

        return freshDivision;
    }
}
