package com.example.cqrs_premium.infrastructure.persistence.mapper;

import com.example.cqrs_premium.domain.model.Order;
import com.example.cqrs_premium.infrastructure.persistence.jpa.entity.OrderJpaEntity;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    private final CustomerMapper customerMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderMapper(CustomerMapper customerMapper, OrderItemMapper orderItemMapper) {
        this.customerMapper = customerMapper;
        this.orderItemMapper = orderItemMapper;
    }

    public Order toDomain(OrderJpaEntity entity) {
        return Order.createOrder(
            entity.getId(),
            customerMapper.toDomain(entity.getCustomer()),
            entity.getOrderItems().stream()
                .map(orderItemMapper::toDomain)
                .collect(Collectors.toList()),
            entity.getOrderDate()
        ).updateStatus(entity.getStatus());
    }

    public OrderJpaEntity toEntity(Order order) {
        OrderJpaEntity entity = new OrderJpaEntity();
        entity.setId(order.getId());
        entity.setCustomer(customerMapper.toEntity(order.getCustomer()));
        entity.setOrderItems(order.getOrderItems().stream()
            .map(orderItemMapper::toEntity)
            .collect(Collectors.toList()));
        entity.setTotalAmount(order.getTotalAmount());
        entity.setOrderDate(order.getOrderDate());
        entity.setStatus(order.getStatus());
        return entity;
    }
}