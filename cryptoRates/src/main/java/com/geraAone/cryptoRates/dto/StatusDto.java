package com.geraAone.cryptoRates.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusDto {
    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("error_code")
    private int errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("elapsed")
    private int elapsed;

    @JsonProperty("credit_count")
    private int creditCount;

    @JsonProperty("notice")
    private String notice;
}
