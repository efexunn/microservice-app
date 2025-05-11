package com.efexunn.notification.email;

import com.efexunn.notification.notification.Notification;
import com.efexunn.notification.notification.NotificationService;
import com.efexunn.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.efexunn.notification.email.EmailTemplateName.SIMPLE_TEMPLATE;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final NotificationService notificationService;


    @PostMapping("/send-template-mail")
    public ResponseEntity<String> sendTemplateMail(@RequestBody MailRequest mailRequest) throws MessagingException {
        emailService.sendTemplateEmail(
                mailRequest.getEmailAddress(),
                mailRequest.getMessage(),
                mailRequest.getSubject(),
                SIMPLE_TEMPLATE
        );

        return ResponseEntity.ok("Template mail sent succesfully. If you are using 'dev' profile, you can check localhost:1080");
    }

    @PostMapping("/send-text-mail")
    public ResponseEntity<String> sendTextMail(@RequestBody MailRequest mailRequest) throws MessagingException {
        emailService.sendTextEmail(
                mailRequest.getEmailAddress(),
                mailRequest.getSubject(),
                mailRequest.getMessage()
        );


        notificationService.saveNotification(
                Notification.builder()
                        .notificationDate(LocalDateTime.now())
                        .notificationType(NotificationType.LOGIN_INFO)
                        .build()
        );

        return ResponseEntity.ok("Text mail sent succesfully. If you are using 'dev' profile, you can check localhost:1080");

    }
}
