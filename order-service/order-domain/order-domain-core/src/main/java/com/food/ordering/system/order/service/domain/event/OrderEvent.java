package com.food.ordering.system.order.service.domain.event;

import com.food.ordering.system.order.service.domain.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderEvent {

    private final ZonedDateTime createdAt;
    private final Order order;

    protected OrderEvent(ZonedDateTime createdAt, Order order) {
        this.createdAt = createdAt;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
