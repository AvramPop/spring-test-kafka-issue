package com.example.demo;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

public class CustomKafkaContainer extends KafkaContainer {
    private static final String KAFKA_DOCKER_IMAGE_NAME = "confluentinc/cp-kafka:6.2.1";

    private static KafkaContainer kafkaContainer;

    private CustomKafkaContainer() {
        super(DockerImageName.parse(KAFKA_DOCKER_IMAGE_NAME));
    }

    public static KafkaContainer getInstance() {
        if (kafkaContainer == null) {
            kafkaContainer = new KafkaContainer(
                    DockerImageName.parse(KAFKA_DOCKER_IMAGE_NAME).asCompatibleSubstituteFor("confluentinc/cp-kafka"))
                    .withEnv("KAFKA_AUTO_CREATE_TOPICS_ENABLE", "true")
                    .withEnv("KAFKA_CREATE_TOPICS", "topic-name")
                    .withStartupAttempts(3)
                    .withStartupTimeout(Duration.ofMinutes(3));
        }
        return kafkaContainer;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
