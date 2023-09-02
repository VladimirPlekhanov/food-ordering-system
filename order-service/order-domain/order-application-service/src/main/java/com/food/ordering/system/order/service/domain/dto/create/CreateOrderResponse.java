package com.food.ordering.system.order.service.domain.dto.create;

import lombok.Builder;

import java.util.UUID;

@Builder
public class CreateOrderResponse {

    UUID trackingId;
    String status;
    String message;
}
