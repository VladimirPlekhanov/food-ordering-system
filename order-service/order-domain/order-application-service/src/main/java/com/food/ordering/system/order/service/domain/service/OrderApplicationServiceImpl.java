package com.food.ordering.system.order.service.domain.service;

import com.food.ordering.system.order.service.domain.OrderProcessingService;
import com.food.ordering.system.order.service.domain.dto.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.port.input.OrderApplicationService;
import com.food.ordering.system.order.service.domain.port.output.OrderRepository;
import com.food.ordering.system.order.service.domain.port.output.RestaurantRepository;
import lombok.Builder;

@Builder
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderRepository orderRepository;
    private final OrderProcessingService orderProcessingService;
    private final OrderDataMapper orderDataMapper;
    private final RestaurantRepository restaurantRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand command) {
        var order = orderDataMapper.toOrder(command);
        orderProcessingService.validateAndInitiateOrder(order, restaurant);
        orderRepository.save(order);
        return orderDataMapper.toCreateOrderResponse(order);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery query) {
        return orderDataMapper.toTrackOrderResponse(query);
    }
}
