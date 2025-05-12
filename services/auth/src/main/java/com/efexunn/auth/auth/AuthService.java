package com.efexunn.auth.auth;

import com.efexunn.auth.exceptions.CustomAuthenticationException;
import com.efexunn.auth.exceptions.CustomJwtException;
import com.efexunn.auth.kafka.LoginNotification;
import com.efexunn.auth.kafka.LoginNotificationProducer;
import com.efexunn.auth.user.*;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LoginNotificationProducer loginNotificationProducer;

    public AuthenticationResponse authenticate(LoginRequest request) {
        try{
             authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

        } catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new CustomAuthenticationException("Incorrect credentials on login : " , e);
        }

        User user = userService.findByEmail(request.getEmail());

        var jwtToken = jwtService.generateToken(user.getEmail(), user.getRole().name());

        loginNotificationProducer.sendLoginNotification(
                LoginNotification.builder()
                        .emailAddress(user.getEmail())
                        .location("Bursa, Turkey")
                        .loginDate(LocalDateTime.now())
                        .build()
        );

        return new AuthenticationResponse(jwtToken);
    }

    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }
}
