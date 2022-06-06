package nl.novi.eindopdracht.dtos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class UserOutputDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String userName;
    private String email;
    private String level;
    private String contactIntensity;
    private boolean isStudent;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String aboutMe;
}
