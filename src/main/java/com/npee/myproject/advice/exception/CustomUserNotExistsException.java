package com.npee.myproject.advice.exception;

public class CustomUserNotExistsException extends RuntimeException {
    public CustomUserNotExistsException() {
        super();
    }

    public CustomUserNotExistsException(String message) {
        super(message);
    }

    public CustomUserNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
