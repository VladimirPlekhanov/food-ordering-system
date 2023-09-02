package com.food.ordering.system.order.service.domain.handler;

import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.port.output.OrderRepository;
import com.food.ordering.system.order.service.domain.value.TrackingId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class TrackOrderQueryHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse handle(TrackOrderQuery query) {
        return orderRepository.findByTrackingId(new TrackingId(query.getTrackingId()))
                .map(orderDataMapper::toTrackOrderResponse)
                .orElseThrow(() -> {
                    log.warn("Could not find order with tracking id: {}", query.getTrackingId());
                    return new OrderNotFoundException("order not found by tracking id");
                });
    }
}
