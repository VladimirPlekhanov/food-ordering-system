package com.food.ordering.system.order.service.data.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "items")
@IdClass(OrderItemEntityId.class)
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemEntity {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    private UUID productId;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subTotal;
}
