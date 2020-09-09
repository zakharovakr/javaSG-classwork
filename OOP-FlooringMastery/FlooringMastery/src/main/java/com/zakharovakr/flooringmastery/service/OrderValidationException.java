package com.zakharovakr.flooringmastery.service;

public class OrderValidationException extends Exception {
    public OrderValidationException(String message) {
        super(message);
    }

    public OrderValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
