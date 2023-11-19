package com.food.ordering.system.order.service.data.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "restaurants_products_m_view", schema = "restaurant")
@IdClass(RestaurantEntityId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantEntity {

    @Id
    private UUID restaurantId;
    @Id
    private UUID productId;
    private Boolean restaurantActive;
    private String productName;
    private BigDecimal productPrice;
}
