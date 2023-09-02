package com.food.ordering.system.order.service.domain.valueObjects;

import com.food.ordering.system.order.service.domain.valueObjects.Identity;

import java.util.UUID;

public class TrackingId extends Identity<UUID> {

    public TrackingId(UUID value) {
        super(value);
    }
}
