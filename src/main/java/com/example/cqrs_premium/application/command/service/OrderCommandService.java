package com.example.cqrs_premium.application.command.service;

import com.example.cqrs_premium.application.command.dto.CreateOrderCommand;
import com.example.cqrs_premium.application.command.dto.CreateOrderCommand.OrderItemDto;
import com.example.cqrs_premium.domain.model.Customer;
import com.example.cqrs_premium.domain.model.Order;
import com.example.cqrs_premium.domain.model.OrderItem;
import com.example.cqrs_premium.domain.model.OrderStatus;
import com.example.cqrs_premium.domain.model.Product;
import com.example.cqrs_premium.domain.repository.CustomerRepository;
import com.example.cqrs_premium.domain.repository.OrderRepository;
import com.example.cqrs_premium.domain.repository.ProductRepository;
import com.example.cqrs_premium.infrastructure.messaging.event.OrderCreatedEvent;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCommandService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Long createOrder(CreateOrderCommand command) {
        Customer customer = customerRepository.findById(command.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<OrderItem> orderItems = command.getItems().stream()
                .map(this::createOrderItem)
                .collect(Collectors.toList());

        Order order = Order.createOrder(null, customer, orderItems, LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        
        eventPublisher.publishEvent(new OrderCreatedEvent(savedOrder.getId()));
        return savedOrder.getId();
    }

    @Transactional
    public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Order updatedOrder = order.updateStatus(newStatus);
        orderRepository.save(updatedOrder);
    }

    private OrderItem createOrderItem(OrderItemDto itemDto) {
        Product product = productRepository.findById(itemDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return new OrderItem(null, product, itemDto.getQuantity(), product.getPrice());
    }
}