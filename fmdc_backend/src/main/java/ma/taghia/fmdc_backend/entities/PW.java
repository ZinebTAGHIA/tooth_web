package ma.taghia.fmdc_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PW {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String objectif;
    private String docs;
    @ManyToMany
    private List<Group> groups;
    @OneToMany(mappedBy = "pw")
    @JsonIgnore
    private List<StudentPW> studentPWS;
    @ManyToOne
    private Tooth tooth;
}
