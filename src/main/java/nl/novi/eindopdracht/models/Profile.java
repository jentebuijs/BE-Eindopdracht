package nl.novi.eindopdracht.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Profile {

    @OneToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String level;
    private String contactIntensity;
    private String aboutMe;
    private boolean isStudent;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getContactIntensity() {
        return contactIntensity;
    }

    public void setContactIntensity(String contactIntensity) {
        this.contactIntensity = contactIntensity;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
