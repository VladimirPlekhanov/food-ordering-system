package com.food.ordering.system.service.domain.dto.message;

import com.food.ordering.system.domain.value.PaymentStatus;

import java.util.List;
import java.util.UUID;

public record PaymentResponse(
        String sagaId,
        UUID orderId,
        PaymentStatus paymentStatus,
        List<String> failureMessages) {
}
