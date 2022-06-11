package nl.novi.eindopdracht.exceptions;

public class AlreadyInUseException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public AlreadyInUseException() {
        super();
    }

    public AlreadyInUseException(String message) {
        super(message);
    }
}

