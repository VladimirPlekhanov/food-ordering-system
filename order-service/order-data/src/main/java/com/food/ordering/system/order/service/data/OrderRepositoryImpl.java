package com.food.ordering.system.order.service.data;

import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.value.TrackingId;
import com.food.ordering.system.service.domain.port.output.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Order save(Order order) {
        return order;
    }

    @Override
    public Optional<Order> findById(UUID orderId) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return Optional.empty();
    }
}
