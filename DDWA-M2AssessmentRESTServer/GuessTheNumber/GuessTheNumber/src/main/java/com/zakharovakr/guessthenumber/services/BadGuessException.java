package com.zakharovakr.guessthenumber.services;

public class BadGuessException extends Exception {
    public BadGuessException(String message) {
        super(message);
    }

    public BadGuessException(String message, Throwable cause) {
        super(message, cause);
    }
}

