package nl.novi.eindopdracht.dtos;

import javax.persistence.Id;
import java.time.LocalDate;

public class UserOutputDto {
    @Id
    private Long id;
    private String username;
    private String email;
    private boolean isStudent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }



}
