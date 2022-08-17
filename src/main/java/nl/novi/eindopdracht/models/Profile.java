package nl.novi.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private String username;

    private boolean isActivated;

    private String firstName;
    private String lastName;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate dob;

    private int age;
    private String email;

    @Enumerated(EnumType.STRING)
    private EAuthority role;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    private String aboutMe;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToOne (cascade = CascadeType.ALL)
    FileUploadResponse photo;

    public Profile(String username, String firstName, String lastName, LocalDate dob, String email, Frequency frequency, String aboutMe, Level level) {
        this.username = username;
        this.isActivated = true;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.age = Period.between(dob, LocalDate.now()).getYears();
        this.email = email;
        this.frequency = frequency;
        this.aboutMe = aboutMe;
        this.level = level;
    }

    public Profile() {
    }

    //--- GETTERS & SETTERS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
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

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EAuthority getRole() {
        return role;
    }

    public void setRole(EAuthority role) {
        this.role = role;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public FileUploadResponse getPhoto() {
        return photo;
    }

    public void setPhoto(FileUploadResponse photo) {
        this.photo = photo;
    }
}
