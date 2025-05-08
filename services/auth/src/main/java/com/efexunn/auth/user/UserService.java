package com.efexunn.auth.user;

import com.efexunn.auth.exceptions.UserAlreadyExistException;
import com.efexunn.auth.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User with this email cannot find."));
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new UserAlreadyExistException("User with this email already exists on system!");
        }

        User newUser = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.CUSTOMER)
                .build();

        User createdUser = userRepository.save(newUser);

        return RegisterResponse.builder()
                .message("New user created with this email : " + createdUser.getEmail())
                .isSuccess(true)
                .build();
    }
}
