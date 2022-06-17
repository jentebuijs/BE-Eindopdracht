package nl.novi.eindopdracht.dtos;

import javax.persistence.Id;

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

    public boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(boolean isStudent) {

        this.isStudent = isStudent;
    }

}
