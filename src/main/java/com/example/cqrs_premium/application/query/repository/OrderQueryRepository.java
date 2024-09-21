package com.example.cqrs_premium.application.query.repository;

import com.example.cqrs_premium.application.query.dto.OrderSummaryDto;
import java.util.List;
import java.util.Optional;

public interface OrderQueryRepository {
    Optional<OrderSummaryDto> findOrderSummaryById(Long orderId);
    List<OrderSummaryDto> findOrderSummariesByCustomerId(Long customerId);
}