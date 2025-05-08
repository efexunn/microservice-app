package com.efexunn.auth.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotEmpty(message = "firstname cannot be empty")
    private String firstname;

    @NotEmpty(message = "firstname cannot be empty")
    private String lastname;

    @NotEmpty(message = "firstname cannot be empty")
    @Email(message = "email format is not correct")
    private String email;

    @NotEmpty(message = "firstname cannot be empty")
    private String password;
}
