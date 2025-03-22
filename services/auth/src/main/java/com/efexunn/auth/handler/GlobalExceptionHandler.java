package com.efexunn.auth.handler;

import com.efexunn.auth.exceptions.UserNotFoundException;
import com.efexunn.auth.user.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class})
    public User handleUserNotFoundException(UserNotFoundException exception){
        return null;
    }
}
