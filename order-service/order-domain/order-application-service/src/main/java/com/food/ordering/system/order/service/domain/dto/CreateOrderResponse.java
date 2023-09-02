package com.food.ordering.system.order.service.domain.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public class CreateOrderResponse {

    UUID trackingId;
    String status;
    String message;
}
