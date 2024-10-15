package com.example.KafkaSpring.kafka;

import com.example.KafkaSpring.entity.Rate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateConsumer {
    private final Logger logger;

    @KafkaListener(topics = "rates-topic",groupId = "rate-group")
    public void consume(Rate rate, Acknowledgment ack) {
        logger.info(String.format("#### -> Consuming message -> %s", rate.toString()));
        ack.acknowledge();
    }
}

