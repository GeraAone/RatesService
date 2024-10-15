package com.example.KafkaSpring.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rate {
    private Long id;

    private Integer currencyId;

    private String name;

    private String symbol;

    private Double price;

    private String updatedAt;

    @Override
    public String toString(){
      return "rate id:" + this.id + " currencyId: " + this.currencyId +
              " name: " + this.name + " price: " + this.price + " updated at: " + this.updatedAt;
    }
}
