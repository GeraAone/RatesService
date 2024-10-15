package com.geraAone.cryptoRates.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "rates", uniqueConstraints = {@UniqueConstraint(columnNames = "currency_id")})
@NoArgsConstructor
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "currency_id", nullable = false)
    private Integer currencyId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Override
    public String toString(){
      return "rate id:" + this.id + " currencyId: " + this.currencyId +
              " name: " + this.name + " price: " + this.price + " updated at: " + this.updatedAt;
    }
}
