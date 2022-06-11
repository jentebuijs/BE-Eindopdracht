package nl.novi.eindopdracht.models;

import javax.persistence.*;

@Entity
@Table(name = "usertable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToOne
    private Profile profile;

    private String username;
    private String password;
    private String email;
    private boolean isStudent;

    public Long getUserId() { return userId; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
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

    public boolean getIsStudent() {
        return isStudent;
    }
}
