package be.switchfully.uno_shark.domain.parking;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DIVISION")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_seq")
    @SequenceGenerator(name = "division_seq", sequenceName = "division_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "PARENT")
    private Division parent;

    @OneToMany(mappedBy = "parent")
    private Set<Division> subdivisions;

    private String name;
    private String originalName;
    //todo
    private String director;

    public Division() {

    }

    public Division(String name, String originalName, String director) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }

    public void setParent(Division parent) {
        this.parent = parent;
    }

    public long getId() {
        return id;
    }

    public Division getParent() {
        return parent;
    }

    public Set<Division> getSubdivisions() {
        return subdivisions;
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


     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return id == division.id && Objects.equals(parent, division.parent) && Objects.equals(subdivisions, division.subdivisions) && Objects.equals(name, division.name) && Objects.equals(originalName, division.originalName) && Objects.equals(director, division.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, originalName, director);
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", parent=" + parent +
                ", subdivisions=" + subdivisions +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", director='" + director + '\'' +
                '}';
    }
}
