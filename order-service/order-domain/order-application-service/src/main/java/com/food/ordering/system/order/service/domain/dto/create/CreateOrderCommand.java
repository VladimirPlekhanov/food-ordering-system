package com.food.ordering.system.order.service.domain.dto.create;

import com.food.ordering.system.order.service.domain.dto.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.OrderItem;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class CreateOrderCommand {

    @NotNull(message = "restaurant id must be not null")
    private UUID restaurantId;
    @NotNull(message = "customer id must be not null")
    private UUID customerId;
    @NotNull(message = "price must be not null")
    @Positive(message = "price must be positive")
    private BigDecimal price;
    @NotNull(message = "items must be not null")
    private List<OrderItem> items;
    @NotNull(message = "address id must be not null")
    private OrderAddress address;
}
