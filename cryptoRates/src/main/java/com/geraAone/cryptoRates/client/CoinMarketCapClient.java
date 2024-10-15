package com.geraAone.cryptoRates.client;

import com.geraAone.cryptoRates.dto.RateDto;
import io.micrometer.core.annotation.Timed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ratesclient", url="${coin-market-cap.url}")
public interface CoinMarketCapClient {
    @Timed("getRatesFromCMC")
    @GetMapping("/cryptocurrency/quotes/latest")
    List<RateDto> getRate();

    @Timed("getRateBySymbolFromCMC")
    @GetMapping("/cryptocurrency/quotes/latest")
    RateDto getRateBySymbol(@RequestParam(name = "symbol") String symbol,
                            @RequestHeader("X-CMC_PRO_API_KEY") String apiKey,
                            @RequestHeader("Accepts") String accepts,
                            @RequestParam(value = "convert", defaultValue = "USD") String convert);
}
