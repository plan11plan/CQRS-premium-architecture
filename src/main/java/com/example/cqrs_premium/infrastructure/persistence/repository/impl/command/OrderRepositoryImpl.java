package com.example.cqrs_premium.infrastructure.persistence.repository.impl.command;

import com.example.cqrs_premium.domain.model.Order;
import com.example.cqrs_premium.domain.repository.OrderRepository;
import com.example.cqrs_premium.infrastructure.persistence.jpa.entity.OrderJpaEntity;
import com.example.cqrs_premium.infrastructure.persistence.jpa.repository.JpaOrderRepository;
import com.example.cqrs_premium.infrastructure.persistence.mapper.OrderMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final JpaOrderRepository jpaOrderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order save(Order order) {
        OrderJpaEntity orderJpaEntity = orderMapper.toEntity(order);
        OrderJpaEntity savedEntity = jpaOrderRepository.save(orderJpaEntity);
        return orderMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaOrderRepository.findByIdWithDetails(id).map(orderMapper::toDomain);
    }
}