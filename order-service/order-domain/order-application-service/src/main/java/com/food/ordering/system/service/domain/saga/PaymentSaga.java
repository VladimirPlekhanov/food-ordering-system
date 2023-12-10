package com.food.ordering.system.service.domain.saga;

import com.food.ordering.system.domain.saga.SagaStep;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.domain.OrderDomainService;
import com.food.ordering.system.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.service.domain.helper.OrderSagaHelper;
import com.food.ordering.system.service.domain.port.output.OrderEventPublisher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PaymentSaga implements SagaStep<PaymentResponse> {

    private final OrderSagaHelper orderSagaHelper;
    private final OrderDomainService orderDomainService;
    private final OrderEventPublisher orderEventPublisher;


    @Override
    public void proceed(PaymentResponse data) {
        final var order =  orderSagaHelper.findOrderById(data.orderId());
        final var event = orderDomainService.payOrder(order);
        orderSagaHelper.saveOrder(order);
        orderEventPublisher.publish(event);
    }

    @Override
    public void rollback(PaymentResponse data) {
        final var order = orderRepository.findById(new OrderId(data.orderId())).orElseThrow();
        orderDomainService.cancelOrder(order, data.failureMessages());
        orderRepository.save(order);
    }
}
