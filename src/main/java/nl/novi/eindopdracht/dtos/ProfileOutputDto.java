package nl.novi.eindopdracht.dtos;

import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.models.Frequency;
import nl.novi.eindopdracht.models.Level;

import javax.persistence.*;

public class ProfileOutputDto {
    @Id
    private String username;

    private String firstName;
    private String lastName;

    private boolean isActivated;

    private int age;
    private String email;
    private String aboutMe;
    private String role;
    private Frequency frequency;
    private Level level;

    FileUploadResponse photo;

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

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
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
