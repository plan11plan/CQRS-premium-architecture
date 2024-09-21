package com.example.cqrs_premium.infrastructure.persistence.mapper;

import com.example.cqrs_premium.domain.model.Customer;
import com.example.cqrs_premium.infrastructure.persistence.jpa.entity.CustomerJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toDomain(CustomerJpaEntity entity) {
        return new Customer(
            entity.getId(),
            entity.getName(),
            entity.getEmail()
        );
    }

    public CustomerJpaEntity toEntity(Customer customer) {
        CustomerJpaEntity entity = new CustomerJpaEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        return entity;
    }
}