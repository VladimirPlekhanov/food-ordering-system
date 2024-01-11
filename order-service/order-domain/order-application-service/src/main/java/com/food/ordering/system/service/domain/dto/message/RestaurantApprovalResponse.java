package com.food.ordering.system.service.domain.dto.message;

import com.food.ordering.system.domain.value.RestaurantApprovalStatus;

import java.util.List;
import java.util.UUID;

public record RestaurantApprovalResponse(
        String sagaId,
        UUID orderId,
        RestaurantApprovalStatus restaurantApprovalStatus,
        List<String> failureMessages) {
}
