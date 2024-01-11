package com.food.ordering.system.service.domain.port.input;

import com.food.ordering.system.service.domain.dto.message.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseListener {

    void process(RestaurantApprovalResponse response);
}
