package com.kys9261.mapsearch.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Not Found resource.");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
