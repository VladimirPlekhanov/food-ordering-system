package com.food.ordering.system.order.service.domain.dto;

import lombok.Builder;

@Builder
public class OrderAddress {

    String street;
    String postalCode;
    String city;
}
