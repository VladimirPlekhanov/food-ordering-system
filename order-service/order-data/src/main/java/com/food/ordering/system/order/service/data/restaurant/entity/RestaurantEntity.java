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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID restaurantId;
    @Id
    @EqualsAndHashCode.Include
    private UUID productId;
    private Boolean restaurantActive;
    private String productName;
    private BigDecimal productPrice;
}
