package ma.taghia.fmdc_backend.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    protected String userName;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String image;
}
