package com.food.ordering.system.service.domain.saga;

import com.food.ordering.system.domain.saga.SagaStep;
import com.food.ordering.system.service.domain.dto.message.RestaurantApprovalResponse;

public class RestarauntApprovalSaga implements SagaStep<RestaurantApprovalResponse> {

    @Override
    public void proceed(RestaurantApprovalResponse data) {

    }

    @Override
    public void rollback(RestaurantApprovalResponse data) {

    }
}
