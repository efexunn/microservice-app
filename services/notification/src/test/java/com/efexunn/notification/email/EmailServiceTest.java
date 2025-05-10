package com.efexunn.notification.email;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    @SneakyThrows
    @Test
    void sendTextEmail() {
        // Arrange
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        SpringTemplateEngine springTemplateEngine = mock(SpringTemplateEngine.class);
        EmailService emailService = new EmailService(javaMailSender, springTemplateEngine);

        // Reflection ile private sender alanını ayarla
        Field senderField = EmailService.class.getDeclaredField("sender");
        senderField.setAccessible(true);
        senderField.set(emailService, "no-reply@example.com");

        String to = "test@example.com";
        String subject = "Test Subject";
        String message = "Hello, this is a test.";

        // Act
        emailService.sendTextEmail(to, subject, message);

        // Assert
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender, times(1)).send(captor.capture());

        SimpleMailMessage sentMessage = captor.getValue();
        assertEquals(to, sentMessage.getTo()[0]);
        assertEquals(subject, sentMessage.getSubject());
        assertEquals(message, sentMessage.getText());
        assertEquals("no-reply@example.com", sentMessage.getFrom());

    }
}