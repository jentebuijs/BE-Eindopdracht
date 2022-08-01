package nl.novi.eindopdracht.models;

import javax.persistence.*;

@Entity
@Table(name="requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User recipient;

    private String message;
    private boolean gotAccepted;
    private boolean gotCanceled;

    public Request(User sender, User recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    public Request() {
    }

    //--- GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isGotAccepted() {
        return gotAccepted;
    }

    public void setGotAccepted(boolean gotAccepted) {
        this.gotAccepted = gotAccepted;
    }

    public boolean isGotCanceled() {
        return gotCanceled;
    }

    public void setGotCanceled(boolean gotCanceled) {
        this.gotCanceled = gotCanceled;
    }
}