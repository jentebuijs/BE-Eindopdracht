package nl.novi.eindopdracht.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profile sender;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profile receiver;

    private String message;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Request(Profile sender, Profile receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.status = Status.PENDING;
    }

    public Request() {
    }

    //--- GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public Profile getSender() {
        return sender;
    }

    public void setSender(Profile sender) {
        this.sender = sender;
    }

    public Profile getReceiver() {
        return receiver;
    }

    public void setReceiver(Profile receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}