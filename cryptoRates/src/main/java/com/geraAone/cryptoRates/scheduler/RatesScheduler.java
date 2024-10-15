package com.geraAone.cryptoRates.scheduler;

import com.geraAone.cryptoRates.service.RateService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatesScheduler {

    private final RateService rateService;
    @Value("${coin-market-cap.symbols}")
    private String symbol;


    @Timed("SchedulerUpdate")
    @Scheduled(fixedRate = 5_000)
    public void updateRates() {
        rateService.updateRate(symbol);
    }
}
