package com.microservice.auth.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final UserService userService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendAuthUserResponse(String message) {
        logger.info(String.format("$$ -> sendAuthUserResponse Producing message --> %s", message));
        this.kafkaTemplate.send(KafkaTopics.AUTH_USER_RESPONSE, userService.getUserByEmail(message).getData());
    }
}