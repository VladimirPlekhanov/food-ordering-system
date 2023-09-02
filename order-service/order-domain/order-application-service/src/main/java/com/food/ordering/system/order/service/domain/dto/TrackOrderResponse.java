package com.food.ordering.system.order.service.domain.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public class TrackOrderResponse {

    UUID trackingId;
    String status;
    List<String> failureMessages;
}
