package com.kys9261.mapsearch.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Need a Authorization.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
