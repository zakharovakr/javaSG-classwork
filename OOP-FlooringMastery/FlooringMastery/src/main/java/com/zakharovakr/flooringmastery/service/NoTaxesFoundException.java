package com.zakharovakr.flooringmastery.service;

public class NoTaxesFoundException extends Exception {
    public NoTaxesFoundException(String message) {
        super(message);
    }

    public NoTaxesFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

