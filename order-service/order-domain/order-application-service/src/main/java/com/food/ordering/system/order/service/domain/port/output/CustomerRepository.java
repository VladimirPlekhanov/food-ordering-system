package com.food.ordering.system.order.service.domain.port.output;

import com.food.ordering.system.domain.value.CustomerId;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.value.TrackingId;

import java.util.Optional;

public interface CustomerRepository {

    boolean existsById(CustomerId customerId);
}
