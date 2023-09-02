package com.food.ordering.system.order.service.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class TrackOrderQuery {

    UUID trackingId;
}
