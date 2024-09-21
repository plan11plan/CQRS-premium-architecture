package com.example.cqrs_premium.domain.model;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class OrderItem {
    private final Long id;
    private final Product product;
    private final int quantity;
    private final BigDecimal price;

    public OrderItem(Long id, Product product, int quantity, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}