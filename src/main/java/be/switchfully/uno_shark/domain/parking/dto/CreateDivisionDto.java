package be.switchfully.uno_shark.domain.parking.dto;

public class CreateDivisionDto {

    private long parentId;
    private String name;
    private String originalName;
    //todo
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
