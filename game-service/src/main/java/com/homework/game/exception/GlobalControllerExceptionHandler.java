package com.homework.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFound() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DeckAlreadyExistsException.class)
    public void handleAlreadyExists() {
        // Nothing to do
    }
}
