package be.switchfully.uno_shark.domain.parking.divisionDto;

import java.util.List;

public class SingleDivisionDto {
    private long id;
    private String parentName;
    private String name;
    private String originalName;
    //todo
    private String director;

    private List<String> subdivisions;

    public SingleDivisionDto(long id, String parentName, String name, String originalName, String director, List<String> subdivisions) {
        this.id = id;
        this.parentName = parentName;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.subdivisions = subdivisions;
    }

    public long getId() {
        return id;
    }

    public String getParentName() {
        return parentName;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getSubdivisions() {
        return subdivisions;
    }
}