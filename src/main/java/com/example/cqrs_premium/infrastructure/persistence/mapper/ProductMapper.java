package com.example.cqrs_premium.infrastructure.persistence.mapper;

import com.example.cqrs_premium.domain.model.Product;
import com.example.cqrs_premium.infrastructure.persistence.jpa.entity.ProductJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(ProductJpaEntity entity) {
        return new Product(
            entity.getId(),
            entity.getName(),
            entity.getPrice()
        );
    }

    public ProductJpaEntity toEntity(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        return entity;
    }
}