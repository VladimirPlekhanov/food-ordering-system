package com.food.ordering.system.service.domain.handler;

import com.food.ordering.system.service.domain.dto.create.CreateOrderDto;
import com.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.service.domain.helper.CreateOrderHelper;
import com.food.ordering.system.service.domain.port.output.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderCommandHandler {

    private final CreateOrderHelper createOrderHelper;
    private final OrderEventPublisher orderEventPublisher;

    @Transactional
    public CreateOrderResponse handle(CreateOrderDto command) {
        final var event = createOrderHelper.saveOrder(command);
        log.info("Created order with id: {}", event.getOrder().getId());
        orderEventPublisher.publish(event);
        return createOrderHelper.prepareResponse(event.getOrder());
    }
}
