package com.food.ordering.system.service.domain.handler;

import com.food.ordering.system.service.domain.dto.create.CreateOrderDto;
import com.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.service.domain.helper.CreateOrderHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderCommandHandler {

    private final CreateOrderHelper createOrderHelper;

    @Transactional
    public CreateOrderResponse handle(CreateOrderDto command) {
        final var event = createOrderHelper.saveOrder(command);
        log.info("Created order with id: {}", event.getOrder().getId());
        return createOrderHelper.prepareResponse(event.getOrder());
    }
}
