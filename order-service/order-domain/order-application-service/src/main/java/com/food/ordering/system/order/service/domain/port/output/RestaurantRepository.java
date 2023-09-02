package com.food.ordering.system.order.service.domain.port.output;

import com.food.ordering.system.order.service.domain.entity.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository {

    Optional<Restaurant> findById(UUID restaurantId);
}