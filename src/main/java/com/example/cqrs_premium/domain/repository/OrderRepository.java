package com.example.cqrs_premium.domain.repository;

import com.example.cqrs_premium.domain.model.Order;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
}