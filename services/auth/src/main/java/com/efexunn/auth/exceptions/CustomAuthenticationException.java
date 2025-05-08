package com.efexunn.auth.exceptions;

public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException(String message, Exception e) {
        super(message, e);
    }
}
