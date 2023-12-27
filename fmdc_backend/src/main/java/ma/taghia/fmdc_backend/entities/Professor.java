package ma.taghia.fmdc_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Professor extends User {
    private String grade;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<Group> groups;
}
