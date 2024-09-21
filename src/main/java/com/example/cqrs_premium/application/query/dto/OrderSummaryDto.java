package com.example.cqrs_premium.application.query.dto;

import com.example.cqrs_premium.domain.model.OrderStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class OrderSummaryDto implements Serializable { // Serializable 구현 추가
    private static final long serialVersionUID = 1L; // 버전 관리를 위한 serialVersionUID 추가

    private final Long id;
    private final String customerName;
    private final BigDecimal totalAmount;
    private final LocalDateTime orderDate;
    private final OrderStatus status;
    private final int itemCount;

    public OrderSummaryDto(Long id, String customerName, BigDecimal totalAmount, LocalDateTime orderDate, OrderStatus status, int itemCount) {
        this.id = id;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.itemCount = itemCount;
    }
}