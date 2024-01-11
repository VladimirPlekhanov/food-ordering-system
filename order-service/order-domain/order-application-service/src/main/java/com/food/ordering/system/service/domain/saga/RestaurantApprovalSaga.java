package com.food.ordering.system.service.domain.saga;

import com.food.ordering.system.domain.saga.SagaStep;
import com.food.ordering.system.order.service.domain.OrderDomainService;
import com.food.ordering.system.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.service.domain.helper.OrderSagaHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantApprovalSaga implements SagaStep<RestaurantApprovalResponse> {

    private final OrderDomainService orderDomainService;
    private final OrderSagaHelper orderSagaHelper;

    @Override
    public void proceed(RestaurantApprovalResponse data) {
        final var order = orderSagaHelper.findOrderById(data.orderId());
        orderDomainService.approveOrder(order);
        orderSagaHelper.saveOrder(order);
    }

    @Override
    public void rollback(RestaurantApprovalResponse data) {
        final var order = orderSagaHelper.findOrderById(data.orderId());
        orderDomainService.cancelOrder(order, data.failureMessages());
        orderSagaHelper.saveOrder(order);
    }
}
