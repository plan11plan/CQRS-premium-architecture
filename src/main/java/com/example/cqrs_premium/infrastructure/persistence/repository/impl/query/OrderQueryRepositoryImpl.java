package com.example.cqrs_premium.infrastructure.persistence.repository.impl.query;

import com.example.cqrs_premium.application.query.dto.OrderSummaryDto;
import com.example.cqrs_premium.application.query.repository.OrderQueryRepository;
import com.example.cqrs_premium.infrastructure.persistence.mongodb.document.OrderDocument;
import com.example.cqrs_premium.infrastructure.persistence.mongodb.repository.MongoOrderQueryRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {
    private final MongoOrderQueryRepository mongoOrderQueryRepository;

    @Override
    public Optional<OrderSummaryDto> findOrderSummaryById(Long orderId) {
        return Optional.ofNullable(mongoOrderQueryRepository.findByOrderId(orderId))
                .map(this::mapToOrderSummaryDto);
    }

    @Override
    public List<OrderSummaryDto> findOrderSummariesByCustomerId(Long customerId) {
        return mongoOrderQueryRepository.findByCustomerId(customerId).stream()
                .map(this::mapToOrderSummaryDto)
                .collect(Collectors.toList());
    }

    private OrderSummaryDto mapToOrderSummaryDto(OrderDocument orderDocument) {
        return new OrderSummaryDto(
                orderDocument.getOrderId(),
                orderDocument.getCustomerName(),
                orderDocument.getTotalAmount(),
                orderDocument.getOrderDate(),
                orderDocument.getStatus(),
                orderDocument.getItemCount()
        );
    }
}