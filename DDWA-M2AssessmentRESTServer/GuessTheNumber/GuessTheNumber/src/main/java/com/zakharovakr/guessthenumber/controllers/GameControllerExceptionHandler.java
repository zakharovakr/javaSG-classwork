package com.zakharovakr.guessthenumber.controllers;

import com.zakharovakr.guessthenumber.services.BadGuessException;
import com.zakharovakr.guessthenumber.services.NoGameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController

//method not supported (using get method instead of post)
public class GameControllerExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<Error> handleMethodNotSupportedException (
            HttpRequestMethodNotSupportedException ex,
            WebRequest request) {
        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BadGuessException.class)
    public final ResponseEntity<Error> handleBadGuessException (BadGuessException ex, WebRequest request){
        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoGameException.class)
    public final ResponseEntity<Error> handleNoGameException (NoGameException ex, WebRequest request){
        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }
}
