package com.geraAone.cryptoRates.mapper;

import com.geraAone.cryptoRates.dto.RateDto;
import com.geraAone.cryptoRates.entity.Rate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RateMapper {

    public List<Rate> toEntity(RateDto rateDto) {
        List<Rate> list = new ArrayList<>();
        rateDto.getData().forEach((key, value) -> {
            Rate rate = new Rate();
            rate.setCurrencyId(value.getId());
            rate.setName(value.getName());
            rate.setSymbol(value.getSymbol());
            rate.setPrice(value.getQuote().getUsd().getPrice());
            rate.setUpdatedAt(value.getLastUpdated());
            list.add(rate);
        });
        return list;
    }
}
