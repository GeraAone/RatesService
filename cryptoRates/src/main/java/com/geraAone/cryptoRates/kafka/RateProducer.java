package com.geraAone.cryptoRates.kafka;

public interface RateProducer<T> {
    void send(T message);
}
