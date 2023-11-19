package com.food.ordering.system.service.domain.port.input.impl;

import com.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.service.domain.handler.CreateOrderCommandHandler;
import com.food.ordering.system.service.domain.handler.TrackOrderQueryHandler;
import com.food.ordering.system.service.domain.port.input.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final CreateOrderCommandHandler createOrderCommandHandler;
    private final TrackOrderQueryHandler trackOrderQueryHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        log.debug("Processing command: {}", command);
        return createOrderCommandHandler.handle(command);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        log.debug("Processing query: {}", query);
        return trackOrderQueryHandler.handle(query);
    }
}
