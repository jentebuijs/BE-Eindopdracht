package nl.novi.eindopdracht.exceptions;

public class NotAllowedException extends RuntimeException {
    public NotAllowedException() {
        super();
    }

    public NotAllowedException(String message) {
        super(message);
    }
}
