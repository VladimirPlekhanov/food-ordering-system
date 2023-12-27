package com.food.ordering.system.service.domain.dto.create;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class CreateOrderResponse {

    private UUID trackingId;
    private String status;
    private String message;
}
