package com.example.cqrs_premium.presentation.api;

import com.example.cqrs_premium.application.command.dto.CreateOrderCommand;
import com.example.cqrs_premium.application.command.service.OrderCommandService;
import com.example.cqrs_premium.domain.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderCommandController {
    private final OrderCommandService orderCommandService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody CreateOrderCommand command) {
        Long orderId = orderCommandService.createOrder(command);
        return ResponseEntity.ok(orderId);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus newStatus) {
        orderCommandService.updateOrderStatus(orderId, newStatus);
        return ResponseEntity.ok().build();
    }
}