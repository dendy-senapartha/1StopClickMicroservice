package com.microservice.order.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    public static final String GID_AUTH_USER_RESPONSE = "authUserGidResponse";

    private Object value = null;

    @KafkaListener(topics = KafkaTopics.AUTH_USER_RESPONSE, groupId = GID_AUTH_USER_RESPONSE)
    public void authUserResponse(ConsumerRecord message) {
        setValue(message.value().toString());
        logger.info(String.format("$$ -> authUserResponse Consumed Message -> %s", message));
    }

    public Object getValue() {
        return value;
    }

    public Object consumeValue() {
        Object result;
        result = value;
        value = null;
        return result;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
