package com.food.ordering.system.service.domain.mapper;

import com.food.ordering.system.domain.value.CustomerId;
import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.ProductId;
import com.food.ordering.system.domain.value.RestaurantId;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.value.StreetAddress;
import com.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDomainMapper {

    public Order toOrder(CreateOrderCommand command) {
        return Order.builder()
                    .customerId(new CustomerId(command.getCustomerId()))
                    .restaurantId(new RestaurantId(command.getRestaurantId()))
                    .deliveryAddress(new StreetAddress(command.getAddress().getStreet(), command.getAddress().getCity()))
                    .items(toOrderItems(command.getItems()))
                    .totalPrice(new Money(command.getPrice()))
                    .build();
    }

    public CreateOrderResponse toCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                                  .trackingId(order.getTrackingId().getValue())
                                  .status(order.getStatus().name())
                                  .build();
    }

    public TrackOrderResponse toTrackOrderResponse(Order order) {
        return new TrackOrderResponse(
                order.getTrackingId().getValue(),
                order.getStatus().name(),
                order.getFailureMessages()
        );
    }

    public Restaurant toRestaurant(CreateOrderCommand command) {
        final var products = command.getItems().stream()
                                    .map(item -> new Product(new ProductId(item.getProductId())))
                                    .toList();
        return Restaurant.builder()
                         .id(new RestaurantId(command.getRestaurantId()))
                         .products(products)
                         .build();
    }

    private List<OrderItem> toOrderItems(@NotNull(message = "items must be not null") List<com.food.ordering.system.service.domain.dto.OrderItem> items) {
        return items.stream().map(item -> OrderItem.builder()
                                                   .product(new Product(new ProductId(item.getProductId())))
                                                   .price(new Money(item.getPrice()))
                                                   .quantity(item.getQuantity())
                                                   .subTotal(new Money(item.getSubTotal()))
                                                   .build())
                    .toList();
    }
}
