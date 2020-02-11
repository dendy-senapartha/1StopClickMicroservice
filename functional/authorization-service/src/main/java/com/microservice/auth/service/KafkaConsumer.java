package com.microservice.auth.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    public static final String GID_AUTH_USER_REQUEST = "authUserGID";

    private final KafkaProducer producer;

    @KafkaListener(topics = KafkaTopics.AUTH_USER_REQUEST, groupId = GID_AUTH_USER_REQUEST)
    public void authUserRequest(String message) {
        logger.info(String.format("$$ -> authUserRequest Consumed Message -> %s", message));
        producer.sendAuthUserResponse(message);
    }
}
