package com.efexunn.notification.kafka;

import com.efexunn.notification.email.EmailService;
import com.efexunn.notification.email.EmailTemplateName;
import com.efexunn.notification.kafka.auth.LoginNotification;
import com.efexunn.notification.notification.Notification;
import com.efexunn.notification.notification.NotificationService;
import com.efexunn.notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;
    private final EmailService emailService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @KafkaListener(topics = "login-info-topic" ,groupId = "authGroup")
    public void consumeLoginNotification(LoginNotification loginNotification) throws MessagingException {
        notificationService.saveNotification(
                Notification.builder()
                        .notificationDate(LocalDateTime.now())
                        .notificationType(NotificationType.LOGIN_INFO)
                .build()
        );

        emailService.sendTemplateEmail(
                loginNotification.getEmailAddress(),
                "Login Information",
                "A login to your account was detected on "+ formatter.format(loginNotification.getLoginDate()) +" from "+ loginNotification.getLocation() + ".",
                EmailTemplateName.LOGIN_INFO_TEMPLATE
        );
        log.info("MAIL SEND.");
    }

}
