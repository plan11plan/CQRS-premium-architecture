package com.example.cqrs_premium.application.query.service;

import com.example.cqrs_premium.application.query.dto.OrderSummaryDto;
import com.example.cqrs_premium.application.query.repository.OrderQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderQueryService {
    private final OrderQueryRepository orderQueryRepository;

    @Cacheable(value = "orderSummary", key = "#orderId")
    public OrderSummaryDto getOrderSummary(Long orderId) {
        return orderQueryRepository.findOrderSummaryById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Cacheable(value = "customerOrders", key = "#customerId")
    public List<OrderSummaryDto> getCustomerOrders(Long customerId) {
        return orderQueryRepository.findOrderSummariesByCustomerId(customerId);
    }
}