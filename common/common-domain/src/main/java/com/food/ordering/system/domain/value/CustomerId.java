package com.food.ordering.system.domain.value;

import java.util.UUID;

public class CustomerId extends Identity<UUID>{

    public CustomerId(UUID value) {
        super(value);
    }
}
