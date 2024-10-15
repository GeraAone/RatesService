package com.geraAone.cryptoRates.repository;

import com.geraAone.cryptoRates.entity.Rate;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    @Timed("getPriceFromDB")
    Rate findRateBySymbol(String symbol);

    @Timed("upsertPrices")
    @Modifying
    @Query(value = "INSERT INTO rates (currency_id, name, symbol, price, updated_at) " +
            "VALUES (:currencyId, :name, :symbol, :price, :updatedAt) " +
            "ON CONFLICT (currency_id) DO UPDATE SET " +
            "name = EXCLUDED.name, " +
            "symbol = EXCLUDED.symbol, " +
            "price = EXCLUDED.price, " +
            "updated_at = EXCLUDED.updated_at",
            nativeQuery = true)
    void upsertRate(@Param("currencyId") Integer currencyId,
                    @Param("name") String name,
                    @Param("symbol") String symbol,
                    @Param("price") Double price,
                    @Param("updatedAt") String updatedAt);
}
