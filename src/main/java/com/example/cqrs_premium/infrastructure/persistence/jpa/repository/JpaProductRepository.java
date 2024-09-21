package com.example.cqrs_premium.infrastructure.persistence.jpa.repository;

import com.example.cqrs_premium.infrastructure.persistence.jpa.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductJpaEntity, Long> {
}