package com.microservice.order.service;

import lombok.RequiredArgsConstructor;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

/*
 * Created by dendy-prtha on 05/02/2020.
 * AuthServiceClient
 */

@Component
@RequiredArgsConstructor
public class AuthServiceClient {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceClient.class);
    private final KafkaProducer producer;
    private final KafkaConsumer consumer;

    public Object getUser(String principal) {
        Object result = null;
        producer.sendMessage(KafkaTopics.AUTH_USER_REQUEST, principal);
        try {
            Awaitility.await().atMost(5, TimeUnit.SECONDS)
                    .until(consumer::getValue, notNullValue());
            result = consumer.consumeValue();
        } catch (ConditionTimeoutException ex) {
            logger.error("Exception : value is null");
        }
        return result;
    }
}
