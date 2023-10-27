package com.food.ordering.system.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderAddress {

    private String street;
    private String city;
}
