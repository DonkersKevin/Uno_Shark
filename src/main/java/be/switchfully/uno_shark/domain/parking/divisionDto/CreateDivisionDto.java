package be.switchfully.uno_shark.domain.parking.divisionDto;

public class CreateDivisionDto {

    private long parentId;
    private String name;
    private String originalName;
    private String director;

    public CreateDivisionDto(long parentId, String name, String originalName, String director) {
        this.parentId = parentId;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }

    public long getParentId() {
        return parentId;
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
}
