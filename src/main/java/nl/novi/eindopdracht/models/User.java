package nl.novi.eindopdracht.models;

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

    @OneToMany(mappedBy = "recipient",
            cascade = CascadeType.ALL)
    Set<Request> incomingRequests;

    @OneToMany(mappedBy = "sender",
            cascade = CascadeType.ALL)
    Set<Request> outgoingRequests;

    @OneToOne (cascade = CascadeType.ALL)
    FileUploadResponse fileUploadResponse;

    private String password;
    private String email;
    private boolean enabled;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public FileUploadResponse getFileUploadResponse() {
        return fileUploadResponse;
    }

    public void setFileUploadResponse(FileUploadResponse fileUploadResponse) {
        this.fileUploadResponse = fileUploadResponse;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
