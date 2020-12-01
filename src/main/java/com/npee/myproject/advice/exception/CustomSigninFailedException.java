package com.npee.myproject.advice.exception;

public class CustomSigninFailedException extends RuntimeException {
    public CustomSigninFailedException() {
        super();
    }

    public CustomSigninFailedException(String message) {
        super(message);
    }

    public CustomSigninFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
