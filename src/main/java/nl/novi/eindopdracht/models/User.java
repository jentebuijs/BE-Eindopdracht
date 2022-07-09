package nl.novi.eindopdracht.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name="users_requests",
            joinColumns = @JoinColumn(name="use_id"),
            inverseJoinColumns = @JoinColumn(name=request_id)
    )
    private Set<Request> requests;

    private String username;
    private String password;
    private String email;
    private boolean isStudent;

    public Long getId() {
        return id;
    }

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

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }
}
