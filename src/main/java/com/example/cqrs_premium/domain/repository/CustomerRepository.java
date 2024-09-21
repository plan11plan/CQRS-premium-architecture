package com.example.cqrs_premium.domain.repository;

import com.example.cqrs_premium.domain.model.Customer;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Long id);
}