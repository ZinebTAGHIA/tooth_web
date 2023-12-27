package ma.taghia.fmdc_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "groupe")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String year;
    @ManyToOne
    private Professor professor;
    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private List<Student> students;
    @ManyToMany(mappedBy = "groups")
    @JsonIgnore
    private List<PW> pws;
}
