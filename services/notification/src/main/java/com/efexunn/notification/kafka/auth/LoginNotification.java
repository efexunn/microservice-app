package com.efexunn.notification.kafka.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginNotification {
    private String emailAddress;
    private LocalDateTime loginDate;
    private String location;
}
