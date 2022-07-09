package nl.novi.eindopdracht.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private Set<User> users;

    private String sender;
    private String recipient;
    private boolean gotAccepted;
    private boolean gotCanceled;

    public Request(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public Request() {

    }

    public Long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
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
