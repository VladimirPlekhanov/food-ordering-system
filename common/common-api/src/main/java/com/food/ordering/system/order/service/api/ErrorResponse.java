package com.food.ordering.system.order.service.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    private String code;
    private String message;
}
