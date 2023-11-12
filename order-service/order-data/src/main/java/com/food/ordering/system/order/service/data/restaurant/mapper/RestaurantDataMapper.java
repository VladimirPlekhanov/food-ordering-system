package com.food.ordering.system.order.service.data.restaurant.mapper;

import com.food.ordering.system.domain.value.ProductId;
import com.food.ordering.system.domain.value.RestaurantId;
import com.food.ordering.system.order.service.data.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class RestaurantDataMapper {

    public List<UUID> toProductIds(Restaurant restaurant) {
        return Optional.ofNullable(restaurant.getProducts()).stream()
                       .flatMap(List::stream)
                       .filter(Objects::nonNull)
                       .map(product -> product.getId().getValue())
                       .toList();
    }

    public Restaurant toRestaurant(List<RestaurantEntity> entities) {
        var id = entities.stream().findFirst().map(entity -> new RestaurantId(entity.getRestaurantId())).orElseThrow();
        var available = entities.stream().findFirst().map(RestaurantEntity::getRestaurantAvailable).orElseThrow();
        var products = entities.stream().map(entity -> new Product(new ProductId(entity.getProductId()))).toList();
        return Restaurant.builder()
                .id(id)
                .available(available)
                .products(products)
                .build();
    }
}
