package com.efexunn.notification.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailRequest {
    private String emailAddress;
    private String subject;
    private String message;

}