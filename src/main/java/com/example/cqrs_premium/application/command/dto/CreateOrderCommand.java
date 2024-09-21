package com.example.cqrs_premium.application.command.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class CreateOrderCommand {
    private final Long customerId;
    private final List<OrderItemDto> items;

    public CreateOrderCommand(Long customerId, List<OrderItemDto> items) {
        this.customerId = customerId;
        this.items = items;
    }

    @Getter
    public static class OrderItemDto {
        private final Long productId;
        private final int quantity;

        public OrderItemDto(Long productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }
}