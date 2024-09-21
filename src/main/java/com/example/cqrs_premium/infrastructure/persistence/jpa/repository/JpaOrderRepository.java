package com.example.cqrs_premium.infrastructure.persistence.jpa.repository;

import com.example.cqrs_premium.infrastructure.persistence.jpa.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaOrderRepository extends JpaRepository<OrderJpaEntity, Long> {
    @Query("SELECT o FROM OrderJpaEntity o LEFT JOIN FETCH o.orderItems WHERE o.id = :id")
    Optional<OrderJpaEntity> findByIdWithDetails(@Param("id") Long id);
}