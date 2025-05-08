package com.efexunn.auth.handler;

import com.efexunn.auth.exceptions.CustomAuthenticationException;
import com.efexunn.auth.exceptions.UserAlreadyExistException;
import com.efexunn.auth.exceptions.UserNotFoundException;
import com.efexunn.auth.user.RegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity handleUserAlreadyExistException(UserAlreadyExistException exception){
        var response = RegisterResponse.builder()
                .message("User with this email already exists on the system.")
                .isSuccess(false)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler({CustomAuthenticationException.class})
    public ResponseEntity<String> handleCustomAuthenticationException(CustomAuthenticationException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
    }


}
