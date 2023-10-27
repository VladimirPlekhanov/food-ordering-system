package com.food.ordering.system.service.domain.dto.create;

import lombok.Builder;

import java.util.UUID;

@Builder
public class CreateOrderResponse {

    private UUID trackingId;
    private String status;
    private String message;
}
