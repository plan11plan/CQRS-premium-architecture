package com.example.cqrs_premium.infrastructure.persistence.mongodb.document;

import com.example.cqrs_premium.domain.model.OrderStatus;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "orders")
public class OrderDocument {
    @Id
    private String id;
    private Long orderId;
    private Long customerId;
    private String customerName;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private int itemCount;
}