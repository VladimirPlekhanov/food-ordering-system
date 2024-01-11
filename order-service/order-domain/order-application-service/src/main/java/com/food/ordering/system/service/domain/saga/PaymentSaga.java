package com.food.ordering.system.service.domain.saga;

import com.food.ordering.system.domain.saga.SagaStep;
import com.food.ordering.system.order.service.domain.OrderDomainService;
import com.food.ordering.system.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.service.domain.helper.OrderSagaHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentSaga implements SagaStep<PaymentResponse> {

    private final OrderDomainService orderDomainService;
    private final OrderSagaHelper orderSagaHelper;

    @Override
    public void proceed(PaymentResponse data) {
        final var order = orderSagaHelper.findOrderById(data.orderId());
        final var event = orderDomainService.payOrder(order);
        orderSagaHelper.saveOrder(order);
        orderSagaHelper.publishEvent(event);
    }

    @Override
    public void rollback(PaymentResponse data) {
        final var order = orderSagaHelper.findOrderById(data.orderId());
        orderDomainService.cancelOrder(order, data.failureMessages());
        orderSagaHelper.saveOrder(order);
    }
}
