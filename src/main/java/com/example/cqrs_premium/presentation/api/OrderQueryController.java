package com.example.cqrs_premium.presentation.api;

import com.example.cqrs_premium.application.query.dto.OrderSummaryDto;
import com.example.cqrs_premium.application.query.service.OrderQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderQueryController {
    private final OrderQueryService orderQueryService;

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderSummaryDto> getOrderSummary(@PathVariable Long orderId) {
        OrderSummaryDto orderSummary = orderQueryService.getOrderSummary(orderId);
        return ResponseEntity.ok(orderSummary);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderSummaryDto>> getCustomerOrders(@PathVariable Long customerId) {
        List<OrderSummaryDto> orders = orderQueryService.getCustomerOrders(customerId);
        return ResponseEntity.ok(orders);
    }
}