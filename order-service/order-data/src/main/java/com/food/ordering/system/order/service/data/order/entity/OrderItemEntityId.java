package com.food.ordering.system.order.service.data.order.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class OrderItemEntityId implements Serializable {

    private Integer id;
    private OrderEntity order;
}
