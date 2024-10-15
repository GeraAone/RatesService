package com.geraAone.cryptoRates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CoinDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("quote")
    private QuoteDto quote;
}
