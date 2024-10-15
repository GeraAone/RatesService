package com.geraAone.cryptoRates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuoteDto {
    @JsonProperty("USD")
    private UsdDto usd;
}
