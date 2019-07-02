package com.kys9261.mapsearch.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("Access Denied.");
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
