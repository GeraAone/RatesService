package com.geraAone.cryptoRates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;


@Data
public class RateDto {
    @JsonProperty("data")
    private Map<String, CoinDto> data;

    @JsonProperty("status")
    private StatusDto status;
}

