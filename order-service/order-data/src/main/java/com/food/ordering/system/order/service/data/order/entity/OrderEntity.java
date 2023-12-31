package com.food.ordering.system.order.service.data.order.entity;

import com.food.ordering.system.domain.value.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    private UUID restaurantId;
    private String street;
    private String city;
    private BigDecimal price;
    private UUID customerId;
    private UUID trackingId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String failureMessages;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;
}
