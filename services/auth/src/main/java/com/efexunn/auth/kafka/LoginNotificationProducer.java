package com.efexunn.auth.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginNotificationProducer {
    private final KafkaTemplate<String, LoginNotification> kafkaTemplate;

    public void sendLoginNotification(LoginNotification loginNotification){
        Message<LoginNotification> message = MessageBuilder
                .withPayload(loginNotification)
                .setHeader(KafkaHeaders.TOPIC, "login-info-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
