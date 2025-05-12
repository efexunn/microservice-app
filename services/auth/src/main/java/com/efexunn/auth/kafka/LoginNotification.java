package com.efexunn.auth.kafka;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginNotification {
    private String emailAddress;
    private LocalDateTime loginDate;
    private String location;
}
