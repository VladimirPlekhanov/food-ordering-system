package com.food.ordering.system.order.service.domain.valueObjects;

import com.food.ordering.system.order.service.domain.valueObjects.Identity;

import java.util.UUID;

public class RestaurantId extends Identity<UUID> {

    protected RestaurantId(UUID value) {
        super(value);
    }
}
