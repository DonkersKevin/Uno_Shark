package be.switchfully.uno_shark.domain.parking.divisionDto;

public class ShowDivisionDto
{
    private long id;
    private String parentName;
    private String name;
    private String originalName;
    //todo
    private String director;

    public ShowDivisionDto(long id, String parentName, String name, String originalName, String director) {
        this.id = id;
        this.parentName = parentName;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
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
}
