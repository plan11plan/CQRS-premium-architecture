package com.example.cqrs_premium.infrastructure.messaging.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderCreatedEvent {
    private final Long orderId;
}