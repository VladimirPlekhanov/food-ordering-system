package com.food.ordering.system.order.service.domain.port.output;

import java.util.UUID;

public interface CustomerRepository {

    Boolean existById(UUID customerId);
}
