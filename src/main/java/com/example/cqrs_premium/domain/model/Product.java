package com.example.cqrs_premium.domain.model;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class Product {
    private final Long id;
    private final String name;
    private final BigDecimal price;

    public Product(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}