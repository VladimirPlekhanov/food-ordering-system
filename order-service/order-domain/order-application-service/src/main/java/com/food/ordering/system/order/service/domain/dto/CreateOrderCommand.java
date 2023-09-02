package com.food.ordering.system.order.service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class CreateOrderCommand {

    UUID restaurantId;
    UUID customerId;
    BigDecimal price;
    List<OrderItem> items;
    OrderAddress address;
}
