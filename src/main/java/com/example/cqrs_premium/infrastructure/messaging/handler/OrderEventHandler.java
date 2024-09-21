package com.example.cqrs_premium.infrastructure.messaging.handler;

import com.example.cqrs_premium.domain.model.Order;
import com.example.cqrs_premium.domain.repository.OrderRepository;
import com.example.cqrs_premium.infrastructure.messaging.event.OrderCreatedEvent;
import com.example.cqrs_premium.infrastructure.persistence.mongodb.document.OrderDocument;
import com.example.cqrs_premium.infrastructure.persistence.mongodb.repository.MongoOrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {
    private final OrderRepository orderRepository;
    private final MongoOrderQueryRepository mongoOrderQueryRepository;

    @EventListener
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderDocument orderDocument = OrderDocument.builder()
                .orderId(order.getId())
                .customerId(order.getCustomer().getId())
                .customerName(order.getCustomer().getName())
                .totalAmount(order.getTotalAmount())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .itemCount(order.getOrderItems().size())
                .build();

        mongoOrderQueryRepository.save(orderDocument);
    }
}