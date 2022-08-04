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
    private String level;
    private Frequency frequency;
    private String aboutMe;

    public Profile(String username, String firstName, String lastName, String dob, String level, Frequency frequency, String aboutMe) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.level = level;
        this.frequency = frequency;
        this.aboutMe = aboutMe;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

}
