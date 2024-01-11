package com.food.ordering.system.service.domain.port.input.impl;

import com.food.ordering.system.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.service.domain.port.input.RestaurantApprovalResponseListener;
import com.food.ordering.system.service.domain.saga.RestaurantApprovalSaga;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RestaurantApprovalResponseListenerImpl implements RestaurantApprovalResponseListener {

    private final RestaurantApprovalSaga restaurantApprovalSaga;

    @Override
    public void process(RestaurantApprovalResponse response) {
        switch (response.restaurantApprovalStatus()) {
            case APPROVED -> restaurantApprovalSaga.proceed(response);
            case REJECTED -> restaurantApprovalSaga.rollback(response);
            default -> log.warn("Unsupported restaurantApproval status: {}", response.restaurantApprovalStatus());
        }
    }
}
