package com.example.cqrs_premium.infrastructure.persistence.mongodb.repository;

import com.example.cqrs_premium.infrastructure.persistence.mongodb.document.OrderDocument;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOrderQueryRepository extends MongoRepository<OrderDocument, String> {
    OrderDocument findByOrderId(Long orderId);
    List<OrderDocument> findByCustomerId(Long customerId);
}