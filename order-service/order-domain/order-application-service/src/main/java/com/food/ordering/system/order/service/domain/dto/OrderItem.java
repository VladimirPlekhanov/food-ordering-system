package com.food.ordering.system.order.service.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public class OrderItem {

    private UUID productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
}
