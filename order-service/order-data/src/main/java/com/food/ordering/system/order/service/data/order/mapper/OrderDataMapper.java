package com.food.ordering.system.order.service.data.order.mapper;

import com.food.ordering.system.domain.value.*;
import com.food.ordering.system.order.service.data.order.entity.OrderEntity;
import com.food.ordering.system.order.service.data.order.entity.OrderItemEntity;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.value.OrderItemId;
import com.food.ordering.system.order.service.domain.value.StreetAddress;
import com.food.ordering.system.order.service.domain.value.TrackingId;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@SuppressWarnings("unused")
public class OrderDataMapper {

    private final static String DELIMITER = ";";

    public Order toOrder(OrderEntity entity) {
        return Order.builder()
                    .id(new OrderId(entity.getId()))
                    .restaurantId(new RestaurantId(entity.getRestaurantId()))
                    .deliveryAddress(new StreetAddress(entity.getStreet(), entity.getCity()))
                    .totalPrice(new Money(entity.getPrice()))
                    .items(entity.getItems().stream().map(this::toOrderItem).toList())
                    .customerId(new CustomerId(entity.getCustomerId()))
                    .trackingId(new TrackingId(entity.getTrackingId()))
                    .status(entity.getStatus())
                    .failureMessage(Arrays.stream(entity.getFailureMessages().split(DELIMITER)).toList())
                    .build();
    }

    public OrderEntity toOrderEntity(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                                             .id(order.getId().getValue())
                                             .restaurantId(order.getRestaurantId().getValue())
                                             .street(order.getDeliveryAddress().street())
                                             .city(order.getDeliveryAddress().city())
                                             .price(order.getTotalPrice().getAmount())
                                             .customerId(order.getCustomerId().getValue())
                                             .trackingId(order.getTrackingId().getValue())
                                             .status(order.getStatus())
                                             .failureMessages(String.join(DELIMITER, order.getFailureMessages()))
                                             .items(Optional.ofNullable(order.getItems())
                                                            .stream()
                                                            .flatMap(List::stream)
                                                            .map(this::toItemEntity)
                                                            .toList())
                                             .build();
        orderEntity.getItems().forEach(i -> i.setOrder(orderEntity));
        return orderEntity;
    }

    public OrderItemEntity toItemEntity(OrderItem orderItem) {
        return OrderItemEntity.builder()
                              .id(orderItem.getId().getValue())
                              .productId(orderItem.getProduct().getId().getValue())
                              .price(orderItem.getPrice().getAmount())
                              .quantity(orderItem.getQuantity())
                              .subTotal(orderItem.getSubTotal().getAmount())
                              .build();
    }

    public OrderItem toOrderItem(OrderItemEntity entity) {
        return OrderItem.builder()
                        .id(new OrderItemId(entity.getId()))
                        .orderId(new OrderId(entity.getOrder().getId()))
                        .product(new Product(new ProductId(entity.getProductId())))
                        .price(new Money(entity.getPrice()))
                        .quantity(entity.getQuantity())
                        .subTotal(new Money(entity.getSubTotal()))
                        .build();
    }
}
