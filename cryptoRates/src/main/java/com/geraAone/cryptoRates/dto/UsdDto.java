package com.geraAone.cryptoRates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsdDto {
    @JsonProperty("price")
    private double price;
}
