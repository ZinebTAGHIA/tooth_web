package ma.taghia.fmdc_backend.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StudentPWKey implements Serializable {
    private int student;
    private int pw;

    public StudentPWKey() {
    }

    public StudentPWKey(int student, int pw) {
        this.student = student;
        this.pw = pw;
    }
}
