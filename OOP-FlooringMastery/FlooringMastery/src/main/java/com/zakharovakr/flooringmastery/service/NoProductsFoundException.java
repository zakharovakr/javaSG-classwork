package com.zakharovakr.flooringmastery.service;

public class NoProductsFoundException extends Exception {
    public NoProductsFoundException (String message) {
        super(message);
    }

    public NoProductsFoundException (String message, Throwable cause) {
        super(message, cause);
    }
}

