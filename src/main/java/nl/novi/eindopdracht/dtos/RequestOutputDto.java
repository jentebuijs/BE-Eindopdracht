package nl.novi.eindopdracht.dtos;

import nl.novi.eindopdracht.models.Request;

import java.util.List;

public class RequestOutputDto {
    private List<Request> accepted;
    private List<Request> pending;
    private List<Request> declined;
    private List<Request> cancelled;

    public List<Request> getPending() {
        return pending;
    }

    public void setPending(List<Request> pending) {
        this.pending = pending;
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
