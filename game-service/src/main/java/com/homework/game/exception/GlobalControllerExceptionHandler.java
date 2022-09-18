package com.homework.game.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> handleAlreadyExists(AlreadyExistsException alreadyExistsException) {
        return new ResponseEntity<>(alreadyExistsException.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidGameGameStateException.class)
    public ResponseEntity<Object> handleInvalidGameState(InvalidGameGameStateException invalidGameGameStateException) {
        return new ResponseEntity<>(invalidGameGameStateException.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
