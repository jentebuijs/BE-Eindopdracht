package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.exceptions.AlreadyInUseException;
import nl.novi.eindopdracht.exceptions.NotAllowedException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> notFoundException(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyInUseException.class)
    public ResponseEntity<Object> usedException(AlreadyInUseException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.IM_USED);
    }

    @ExceptionHandler(value = NotAllowedException.class)
    public ResponseEntity<Object> notAllowedException(NotAllowedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
