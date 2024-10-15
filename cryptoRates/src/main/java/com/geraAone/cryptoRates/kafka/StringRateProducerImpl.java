package com.geraAone.cryptoRates.kafka;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Qualifier("StringProducer")
public class StringRateProducerImpl implements RateProducer<String>{
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Logger logger;
    @Value("${rates-topic.name}")
    private String topicName;

    @Timed
    public void send(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        CompletableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(topicName, message);
    }
}