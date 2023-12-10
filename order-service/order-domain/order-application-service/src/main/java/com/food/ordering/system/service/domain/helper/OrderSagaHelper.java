package com.food.ordering.system.service.domain.helper;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.service.domain.port.output.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class OrderSagaHelper {
    private final OrderRepository orderRepository;

    public Order findOrderById(UUID orderId) {
        return orderRepository.findById(new OrderId(orderId)).orElseThrow(() -> {
            log.error("Could not find order with id={}", orderId);
            return new OrderNotFoundException("order does not exist");
        });
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
