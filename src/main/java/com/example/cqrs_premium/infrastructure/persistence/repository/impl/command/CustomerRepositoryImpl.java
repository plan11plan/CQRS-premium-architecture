package com.example.cqrs_premium.infrastructure.persistence.repository.impl.command;

import com.example.cqrs_premium.domain.model.Customer;
import com.example.cqrs_premium.domain.repository.CustomerRepository;
import com.example.cqrs_premium.infrastructure.persistence.jpa.repository.JpaCustomerRepository;
import com.example.cqrs_premium.infrastructure.persistence.mapper.CustomerMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final JpaCustomerRepository jpaCustomerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaCustomerRepository.findById(id).map(customerMapper::toDomain);
    }
}