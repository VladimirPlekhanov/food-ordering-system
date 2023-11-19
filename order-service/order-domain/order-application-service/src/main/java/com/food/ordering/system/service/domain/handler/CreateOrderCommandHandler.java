package com.food.ordering.system.service.domain.handler;

import com.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
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
    public CreateOrderResponse handle(CreateOrderCommand command) {
        final var event = createOrderHelper.persistOrder(command);
        log.info("Created order with id: {}", event.getOrder().getId());
        return createOrderHelper.prepareResponse(event.getOrder());
    }
}
