package com.food.ordering.system.service.domain.port.output;

import com.food.ordering.system.order.service.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> find(Restaurant restaurant);
}
