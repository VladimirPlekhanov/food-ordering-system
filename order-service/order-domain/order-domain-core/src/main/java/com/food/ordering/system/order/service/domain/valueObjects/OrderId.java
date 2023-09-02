package com.food.ordering.system.order.service.domain.valueObjects;

import com.food.ordering.system.order.service.domain.valueObjects.Identity;

import java.util.UUID;

public class OrderId extends Identity<UUID> {

    public OrderId(UUID value) {
        super(value);
    }
}
