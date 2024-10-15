package com.geraAone.cryptoRates.kafka;

import com.geraAone.cryptoRates.entity.Rate;
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
@Qualifier("JsonProducer")
public class JsonRateProducerImpl implements RateProducer<Rate>{
    private final KafkaTemplate<String, Rate> kafkaTemplate;
    private final Logger logger;
    @Value("${rates-topic.name}")
    private String topicName;

    @Timed
    public void send(Rate rate) {
        logger.info(String.format("#### -> Producing message -> %s", rate));
        CompletableFuture<SendResult<String, Rate>> sendResult = kafkaTemplate.send(topicName, rate);
    }
}
