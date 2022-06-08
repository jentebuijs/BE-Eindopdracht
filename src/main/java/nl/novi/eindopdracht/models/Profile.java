package nl.novi.eindopdracht.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

//@Entity
public class Profile {

    @OneToOne
    private User user;

    @Id
    Long id = user.getId();

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String level;
    private String contactIntensity;
    private String aboutMe;
    private boolean isStudent;
}
