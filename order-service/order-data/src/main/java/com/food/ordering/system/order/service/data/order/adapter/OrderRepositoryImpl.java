package com.food.ordering.system.order.service.data.order.adapter;

import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.data.order.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.data.order.repository.OrderJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.value.TrackingId;
import com.food.ordering.system.service.domain.port.output.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataMapper orderDataMapper;

    @Override
    public void save(Order order) {
        orderJpaRepository.save(orderDataMapper.toOrderEntity(order));
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderJpaRepository.findById(orderId.getValue()).map(orderDataMapper::toOrder);
    }

    @Override
    public Optional<Order> findByTrackingId(TrackingId trackingId) {
        return orderJpaRepository.findById(trackingId.getValue()).map(orderDataMapper::toOrder);
    }
}
