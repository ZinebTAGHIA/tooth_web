package ma.taghia.fmdc_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student extends User {
    private String number;
    @ManyToMany
    private List<Group> groups;
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<StudentPW> studentPWS;
}
