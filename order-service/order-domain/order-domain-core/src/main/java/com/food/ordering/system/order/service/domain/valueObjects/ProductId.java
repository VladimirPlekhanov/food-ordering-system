package com.food.ordering.system.order.service.domain.valueObjects;

import java.util.UUID;

public class ProductId extends Identity<UUID> {

    protected ProductId(UUID value) {
        super(value);
    }
}
