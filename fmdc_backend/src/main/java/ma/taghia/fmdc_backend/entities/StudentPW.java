package ma.taghia.fmdc_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class StudentPW {
    @EmbeddedId
    private StudentPWKey studentPWKey;
    @JoinColumn(name = "student", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Student student;
    @JoinColumn(name = "pw", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private PW pw;
    private String time;
    private String imageFront;
    private String imageSide;
    @Temporal(TemporalType.DATE)
    private Date date;
}
