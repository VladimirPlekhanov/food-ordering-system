package com.food.ordering.system.service.domain.port.output;

import com.food.ordering.system.order.service.domain.event.OrderEvent;

public interface OrderEventPublisher {

    void publish(OrderEvent event);
}
