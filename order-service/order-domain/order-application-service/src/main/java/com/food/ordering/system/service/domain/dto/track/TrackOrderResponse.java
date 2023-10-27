package com.food.ordering.system.service.domain.dto.track;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TrackOrderResponse {

    private UUID trackId;
    private String status;
    private List<String> failureMessages;
}
