package com.example.cqrs_premium.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

@Getter
public class Order {
    private final Long id;
    private final Customer customer;
    private final List<OrderItem> orderItems;
    private final BigDecimal totalAmount;
    private final LocalDateTime orderDate;
    private final OrderStatus status;

    private Order(Long id, Customer customer, List<OrderItem> orderItems, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.customer = customer;
        this.orderItems = Collections.unmodifiableList(orderItems);
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = calculateTotalAmount();
    }

    public static Order createOrder(Long id, Customer customer, List<OrderItem> orderItems, LocalDateTime orderDate) {
        return new Order(id, customer, orderItems, orderDate, OrderStatus.CREATED);
    }

    public Order updateStatus(OrderStatus newStatus) {
        return new Order(this.id, this.customer, this.orderItems, this.orderDate, newStatus);
    }

    private BigDecimal calculateTotalAmount() {
        return orderItems.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}