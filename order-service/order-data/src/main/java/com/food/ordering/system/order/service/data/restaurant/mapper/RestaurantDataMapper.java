package com.food.ordering.system.order.service.data.restaurant.mapper;

import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.ProductId;
import com.food.ordering.system.domain.value.RestaurantId;
import com.food.ordering.system.order.service.data.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
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
        var available = entities.stream().findFirst().map(RestaurantEntity::getRestaurantActive).orElseThrow();
        var products = entities.stream().map(entity -> Product.builder()
                        .id(new ProductId(entity.getProductId()))
                        .name(entity.getProductName())
                        .price(new Money(entity.getProductPrice()))
                        .build())
                .toList();
        return Restaurant.builder()
                .id(id)
                .available(available)
                .products(products)
                .build();
    }
}
