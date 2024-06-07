package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.time.Duration;
import java.time.Instant;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(initializers = TestContainersInitializer.class)
class DemoApplicationTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @SpyBean
    private TestConsumer testConsumer;

    @Test
    void processLeaderboardStandingEvent() {
        kafkaTemplate.send("topic-name", "key", "value");

        await()
            .pollInterval(Duration.ofSeconds(1))
            .atMost(5, SECONDS)
            .untilAsserted(() -> verify(testConsumer).consume(any()));
    }

}
