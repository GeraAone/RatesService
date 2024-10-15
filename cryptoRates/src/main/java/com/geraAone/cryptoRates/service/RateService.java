package com.geraAone.cryptoRates.service;

import com.geraAone.cryptoRates.client.CoinMarketCapClient;
import com.geraAone.cryptoRates.dto.RateDto;
import com.geraAone.cryptoRates.entity.Rate;
import com.geraAone.cryptoRates.kafka.RateProducer;
import com.geraAone.cryptoRates.mapper.RateMapper;
import com.geraAone.cryptoRates.repository.RateRepository;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class RateService {
    private final RateRepository repository;
    private final RateProducer<Rate> rateProducer;
    private final RateMapper mapper;
    private final CoinMarketCapClient client;
    private final MeterRegistry meterRegistry;
    private final Logger logger;
    @Value("${coin-market-cap.api-key}")
    private String apiKey;
    @Value("${coin-market-cap.accepts}")
    private String accepts;
    private Map<String, AtomicReference<Double>> prices;

    public RateService(RateRepository repository, RateMapper mapper, CoinMarketCapClient client, MeterRegistry meterRegistry, Logger logger, @Qualifier("JsonProducer") RateProducer<Rate> rateProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.client = client;
        this.logger = logger;
        this.prices = new ConcurrentHashMap<>();
        this.meterRegistry = meterRegistry;
        this.rateProducer = rateProducer;
        prices.forEach((symbol, priceRef) ->
                meterRegistry.gauge("coinPrice", Tags.of("currency", symbol), priceRef, AtomicReference::get));
    }


    @Transactional
    public void updateRate(String symbol) {
        RateDto dto =  this.client.getRateBySymbol(symbol, this.apiKey, this.accepts, null);
        List<Rate> rateList = this.mapper.toEntity(dto);
        rateList.forEach(rate -> {
            logger.info("upserted rate: {}", rate.toString());
            rateProducer.send(rate);
            repository.upsertRate(rate.getCurrencyId(), rate.getName(), rate.getSymbol(), rate.getPrice(), rate.getUpdatedAt());
            AtomicReference<Double> priceRef = prices.computeIfAbsent(rate.getSymbol(), k -> new AtomicReference<>(rate.getPrice()));
            priceRef.set(rate.getPrice());
            meterRegistry.gauge("coinPrice", Tags.of("currency", rate.getSymbol()), priceRef, AtomicReference::get);
        });
    }



    @Transactional
    public List<Rate> getAllRates() {
        return repository.findAll();
    }

    @Transactional
    public Rate getRateBySymbol(String symbol) {
        return repository.findRateBySymbol(symbol);
    }
}
