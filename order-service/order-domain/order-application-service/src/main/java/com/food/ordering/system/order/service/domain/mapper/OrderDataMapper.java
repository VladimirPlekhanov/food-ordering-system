package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.order.service.domain.dto.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Order;

public class OrderDataMapper {

    public Order toOrder(CreateOrderCommand command) {
        return null;
    }

    public CreateOrderResponse toCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder().trackingId(order.getTrackingId().getValue()).build();
    }

    public TrackOrderResponse toTrackOrderResponse(TrackOrderQuery trackOrderQuery) {
        return TrackOrderResponse.builder().trackingId(trackOrderQuery.getTrackingId()).build();
    }
}
