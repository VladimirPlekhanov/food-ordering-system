package com.food.ordering.system.order.service.data.restaurant.repository;

import com.food.ordering.system.order.service.data.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.order.service.data.restaurant.entity.RestaurantEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, RestaurantEntityId> {

    List<RestaurantEntity> findByRestaurantIdAndProductIdsIn(UUID restaurantId, List<UUID> productIds);
}
