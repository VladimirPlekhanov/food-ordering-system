package com.food.ordering.system.order.service.data.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "restaurants", schema = "restaurant")
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
    private Boolean restaurantAvailable;
    private String productName;
    private BigDecimal productPrice;
}
