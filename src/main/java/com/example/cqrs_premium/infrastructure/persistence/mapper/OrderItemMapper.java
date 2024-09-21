package com.example.cqrs_premium.infrastructure.persistence.mapper;

import com.example.cqrs_premium.domain.model.OrderItem;
import com.example.cqrs_premium.infrastructure.persistence.jpa.entity.OrderItemJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    private final ProductMapper productMapper;

    public OrderItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderItem toDomain(OrderItemJpaEntity entity) {
        return new OrderItem(
            entity.getId(),
            productMapper.toDomain(entity.getProduct()),
            entity.getQuantity(),
            entity.getPrice()
        );
    }

    public OrderItemJpaEntity toEntity(OrderItem orderItem) {
        OrderItemJpaEntity entity = new OrderItemJpaEntity();
        entity.setId(orderItem.getId());
        entity.setProduct(productMapper.toEntity(orderItem.getProduct()));
        entity.setQuantity(orderItem.getQuantity());
        entity.setPrice(orderItem.getPrice());
        return entity;
    }
}