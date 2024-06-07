package com.example.demo;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.lifecycle.Startables;

public class TestContainersInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final KafkaContainer kafkaContainer;

    static {
        kafkaContainer = CustomKafkaContainer.getInstance();
        Startables.deepStart(kafkaContainer).join();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "spring.kafka.bootstrap-servers=" + kafkaContainer.getBootstrapServers(),
                "spring.kafka.security.protocol=" + "PLAINTEXT"
        ).applyTo(applicationContext.getEnvironment());
    }
}
