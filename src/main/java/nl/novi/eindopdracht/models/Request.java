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
    private User receiver;

    private String message;
    private boolean gotAccepted;
    private boolean gotDeclined;
    private boolean gotCanceled;

    public Request(User sender, User receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
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

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User recipient) {
        this.receiver = recipient;
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

    public boolean isGotDeclined() {
        return gotDeclined;
    }

    public void setGotDeclined(boolean gotDeclined) {
        this.gotDeclined = gotDeclined;
    }

    public boolean isGotCanceled() {
        return gotCanceled;
    }

    public void setGotCanceled(boolean gotCanceled) {
        this.gotCanceled = gotCanceled;
    }
}