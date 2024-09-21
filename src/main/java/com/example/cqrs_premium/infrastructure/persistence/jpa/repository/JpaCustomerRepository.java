package com.example.cqrs_premium.infrastructure.persistence.jpa.repository;

import com.example.cqrs_premium.infrastructure.persistence.jpa.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {
}