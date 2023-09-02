package com.food.ordering.system.order.service.domain.port.output;

import com.food.ordering.system.order.service.domain.entity.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    Order save(Order order);
    Optional<Order> findById(UUID orderId);
}
