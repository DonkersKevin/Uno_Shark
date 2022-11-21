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
    private Long id;

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

    public Long getId() {
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
}
