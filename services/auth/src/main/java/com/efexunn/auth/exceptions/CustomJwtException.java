package com.efexunn.auth.exceptions;

public class CustomJwtException extends RuntimeException {
    public CustomJwtException(String message, Exception e) {
        super(message, e);
    }
}
