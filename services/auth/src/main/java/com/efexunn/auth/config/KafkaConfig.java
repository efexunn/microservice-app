package com.efexunn.auth.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic loginInfoTopic() {
        return TopicBuilder
                .name("login-info-topic")
                .build();
    }
}
