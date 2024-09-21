package com.example.cqrs_premium.infrastructure.persistence.repository.impl.command;

import com.example.cqrs_premium.domain.model.Product;
import com.example.cqrs_premium.domain.repository.ProductRepository;
import com.example.cqrs_premium.infrastructure.persistence.jpa.repository.JpaProductRepository;
import com.example.cqrs_premium.infrastructure.persistence.mapper.ProductMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id).map(productMapper::toDomain);
    }
}