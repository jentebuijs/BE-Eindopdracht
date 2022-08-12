package nl.novi.eindopdracht.models;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private String username;

    private String firstName;
    private String lastName;
    private String dob;
    private String email;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    private String aboutMe;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToOne (cascade = CascadeType.ALL)
    FileUploadResponse fileUploadResponse;

    public Profile(String username, String firstName, String lastName, String dob, String email, Frequency frequency, String aboutMe, Level level) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public FileUploadResponse getFileUploadResponse() {
        return fileUploadResponse;
    }

    public void setFileUploadResponse(FileUploadResponse fileUploadResponse) {
        this.fileUploadResponse = fileUploadResponse;
    }
}
