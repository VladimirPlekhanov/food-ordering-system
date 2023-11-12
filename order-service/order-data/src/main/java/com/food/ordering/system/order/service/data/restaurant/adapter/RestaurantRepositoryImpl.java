package com.food.ordering.system.order.service.data.restaurant.adapter;

import com.food.ordering.system.order.service.data.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.data.restaurant.mapper.RestaurantDataMapper;
import com.food.ordering.system.order.service.data.restaurant.repository.RestaurantJpaRepository;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.service.domain.port.output.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final RestaurantDataMapper restaurantDataMapper;

    @Override
    public Optional<Restaurant> find(Restaurant restaurant) {
        List<RestaurantEntity> entities = restaurantJpaRepository.findByRestaurantIdAndProductIdsIn(
                restaurant.getId().getValue(),
                restaurantDataMapper.toProductIds(restaurant));
        return entities.isEmpty() ? Optional.empty() : Optional.of(restaurantDataMapper.toRestaurant(entities));
    }
}
