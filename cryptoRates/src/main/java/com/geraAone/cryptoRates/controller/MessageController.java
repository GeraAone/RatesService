package com.geraAone.cryptoRates.controller;

import com.geraAone.cryptoRates.kafka.RateProducer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    private final RateProducer<String> rateProducer;

    public MessageController(@Qualifier("StringProducer") RateProducer<String> rateProducer) {
        this.rateProducer = rateProducer;
    }

    @PostMapping("kafka/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        rateProducer.send(message);
        return ResponseEntity.ok("Success");
    }
}
