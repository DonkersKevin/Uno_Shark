package be.switchfully.uno_shark.domain.parking.divisionDto;

import be.switchfully.uno_shark.domain.parking.Division;
import be.switchfully.uno_shark.domain.parking.divisionDto.CreateDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.ShowDivisionDto;
import be.switchfully.uno_shark.domain.parking.divisionDto.SingleDivisionDto;
import be.switchfully.uno_shark.repositories.DivisionRepository;
import be.switchfully.uno_shark.services.GeneralValidationService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DivisionMapper {
    private DivisionRepository divisionRepository;
    private GeneralValidationService generalValidationService;

    public DivisionMapper(DivisionRepository divisionRepository, GeneralValidationService generalValidationService) {
        this.divisionRepository = divisionRepository;
        this.generalValidationService = generalValidationService;
    }

    public Division mapToDivision(CreateDivisionDto createDivisionDto) {
        Division freshDivision = new Division(createDivisionDto.getName(), createDivisionDto.getOriginalName(), createDivisionDto.getDirector());

        if (createDivisionDto.getParentId() == 0 || !divisionRepository.existsById(createDivisionDto.getParentId())) {
            return freshDivision;
        }

        freshDivision.setParent(divisionRepository.findById(createDivisionDto.getParentId()).orElseThrow());

        return freshDivision;
    }

    public List<ShowDivisionDto> mapToDto(List<Division> divisions) {
        return divisions.stream().map(this::mapEachDivisionDto).toList();
    }

    public ShowDivisionDto mapEachDivisionDto(Division division){
        return new ShowDivisionDto(division.getId(), extractParentName(division), division.getName(), division.getOriginalName(), division.getDirector());
    }

    public SingleDivisionDto mapSingleDivisionDto(Division division){
        return new SingleDivisionDto(division.getId(), extractParentName(division), division.getName(), division.getOriginalName(), division.getDirector(), extractSubdivisions(division));
    }

    public List<String> extractSubdivisions(Division division){
        return division.getSubdivisions().stream().map(Division::getName).toList();
    }

    public String extractParentName(Division division){
        if(division.getParent() == null){
            return "No Parent Division";
        }
        return division.getParent().getName();
    }

}
