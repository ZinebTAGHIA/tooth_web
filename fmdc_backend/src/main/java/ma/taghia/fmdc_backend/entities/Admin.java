package ma.taghia.fmdc_backend.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Admin extends User{

    @Override
    public String role() {
        return "ADMIN";
    }
}
