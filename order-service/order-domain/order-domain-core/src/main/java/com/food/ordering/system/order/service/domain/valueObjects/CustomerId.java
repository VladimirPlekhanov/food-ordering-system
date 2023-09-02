package com.food.ordering.system.order.service.domain.valueObjects;

import java.util.UUID;

public class CustomerId extends Identity<UUID> {

    protected CustomerId(UUID value) {
        super(value);
    }
}
