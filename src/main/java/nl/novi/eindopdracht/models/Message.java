package nl.novi.eindopdracht.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private boolean forBuddy;
    private boolean forStudent;
    private boolean approved;

    //--- GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isForBuddy() {
        return forBuddy;
    }

    public void setForBuddy(boolean forBuddy) {
        this.forBuddy = forBuddy;
    }

    public boolean isForStudent() {
        return forStudent;
    }

    public void setForStudent(boolean forStudent) {
        this.forStudent = forStudent;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
