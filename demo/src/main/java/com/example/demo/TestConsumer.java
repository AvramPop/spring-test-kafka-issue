package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConsumer.class);

    @KafkaListener(topics = "topic-name", groupId = "group-id", containerFactory = "testContainerFactory")
    public void consume(String message) {
        LOGGER.info("message for testing: {}", message);
    }
}
