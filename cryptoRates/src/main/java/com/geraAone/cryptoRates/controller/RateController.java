package com.geraAone.cryptoRates.controller;

import com.geraAone.cryptoRates.entity.Rate;
import com.geraAone.cryptoRates.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RateController {
    private final RateService service;

    @GetMapping("crypto-api/rate")
    public List<Rate> getRates() {
        return service.getAllRates();
    }

    @GetMapping("crypto-api/rate/{symbol}")
    public Rate getRate(@PathVariable("symbol") String symbol) {
        return service.getRateBySymbol(symbol);
    }
}
