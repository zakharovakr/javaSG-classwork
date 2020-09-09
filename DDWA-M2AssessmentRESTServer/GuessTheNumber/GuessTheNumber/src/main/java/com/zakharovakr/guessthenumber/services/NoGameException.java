package com.zakharovakr.guessthenumber.services;

public class NoGameException extends Exception {
    public NoGameException(String message) {
        super(message);
    }

    public NoGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
