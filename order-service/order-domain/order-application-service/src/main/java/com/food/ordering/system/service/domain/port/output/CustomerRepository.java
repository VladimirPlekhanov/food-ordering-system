package com.food.ordering.system.service.domain.port.output;

import com.food.ordering.system.domain.value.CustomerId;

public interface CustomerRepository {

    boolean existById(CustomerId customerId);
}
