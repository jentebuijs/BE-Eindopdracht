package nl.novi.eindopdracht.models;

import nl.novi.eindopdracht.dtos.UserInputDto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile")
    private Profile profile;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_username"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities = new HashSet<>();

    private String password;
    private boolean enabled;

    public User (UserInputDto userInputDto) {
        username = userInputDto.getUsername();
        password = userInputDto.getPassword();
        profile = new Profile(userInputDto.getUsername(), userInputDto.getFirstName(), userInputDto.getLastName(),
                userInputDto.getDob(), userInputDto.getEmail(), userInputDto.getFrequency(), userInputDto.getAboutMe(),
                userInputDto.getLevel());
    }

    public User() {
    }

    //--- GETTERS & SETTERS
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
