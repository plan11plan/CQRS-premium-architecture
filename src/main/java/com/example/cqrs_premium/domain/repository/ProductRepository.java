package com.example.cqrs_premium.domain.repository;

import com.example.cqrs_premium.domain.model.Product;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}