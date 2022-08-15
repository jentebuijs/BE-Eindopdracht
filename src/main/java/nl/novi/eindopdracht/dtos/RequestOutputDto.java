package nl.novi.eindopdracht.dtos;

import nl.novi.eindopdracht.models.Request;

import java.util.List;

public class RequestOutputDto {
    private List<Request> incoming;
    private List<Request> outgoing;
    private List<Request> accepted;
    private List<Request> declined;
    private List<Request> cancelled;

    public List<Request> getIncoming() {
        return incoming;
    }

    public void setIncoming(List<Request> incoming) {
        this.incoming = incoming;
    }

    public List<Request> getOutgoing() {
        return outgoing;
    }

    public void setOutgoing(List<Request> outgoing) {
        this.outgoing = outgoing;
    }

    public List<Request> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<Request> accepted) {
        this.accepted = accepted;
    }

    public List<Request> getDeclined() {
        return declined;
    }

    public void setDeclined(List<Request> declined) {
        this.declined = declined;
    }

    public List<Request> getCancelled() {
        return cancelled;
    }

    public void setCancelled(List<Request> cancelled) {
        this.cancelled = cancelled;
    }
}
