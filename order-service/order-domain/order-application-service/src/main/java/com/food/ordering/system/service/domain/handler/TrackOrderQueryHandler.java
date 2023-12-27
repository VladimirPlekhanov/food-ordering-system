package com.food.ordering.system.service.domain.handler;

import com.food.ordering.system.order.service.domain.exception.OrderNotFoundException;
import com.food.ordering.system.order.service.domain.value.TrackingId;
import com.food.ordering.system.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.service.domain.mapper.OrderDomainMapper;
import com.food.ordering.system.service.domain.port.output.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class TrackOrderQueryHandler {

    private final OrderDomainMapper orderDomainMapper;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public TrackOrderResponse handle(TrackOrderQuery query) {
        return orderRepository.findByTrackingId(new TrackingId(query.getTrackingId()))
                              .map(orderDomainMapper::toTrackOrderResponse)
                              .orElseThrow(() -> {
                                  log.warn("Could not find order with tracking id: {}", query.getTrackingId());
                                  return new OrderNotFoundException("order not found by tracking id");
                              });
    }
}
