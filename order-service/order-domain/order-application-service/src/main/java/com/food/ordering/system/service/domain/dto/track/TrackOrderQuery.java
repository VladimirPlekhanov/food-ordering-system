package com.food.ordering.system.service.domain.dto.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TrackOrderQuery {

    @NotNull(message = "tracking id must be not null")
    private UUID trackingId;
}
