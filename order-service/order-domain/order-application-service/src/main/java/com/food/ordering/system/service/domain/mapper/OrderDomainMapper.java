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
import com.food.ordering.system.service.domain.dto.OrderItemDto;
import com.food.ordering.system.service.domain.dto.create.CreateOrderDto;
import com.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDomainMapper {

    public Order toOrder(CreateOrderDto dto) {
        return Order.builder()
                    .customerId(new CustomerId(dto.getCustomerId()))
                    .restaurantId(new RestaurantId(dto.getRestaurantId()))
                    .deliveryAddress(new StreetAddress(dto.getAddress().getStreet(), dto.getAddress().getCity()))
                    .items(toOrderItems(dto.getItems()))
                    .totalPrice(new Money(dto.getPrice()))
                    .build();
    }

    public CreateOrderResponse toCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                                  .trackingId(order.getTrackingId().getValue())
                                  .status(order.getStatus().name())
                                  .message("Order was created successfully")
                                  .build();
    }

    public TrackOrderResponse toTrackOrderResponse(Order order) {
        return new TrackOrderResponse(
                order.getTrackingId().getValue(),
                order.getStatus().name(),
                order.getFailureMessages()
        );
    }

    public Restaurant toRestaurant(CreateOrderDto dto) {
        final var products = dto.getItems().stream()
                                .map(item -> Product.builder()
                                                    .id(new ProductId(item.getProductId()))
                                                    .price(new Money(item.getPrice()))
                                                    .build())
                                .toList();
        return Restaurant.builder()
                         .id(new RestaurantId(dto.getRestaurantId()))
                         .products(products)
                         .build();
    }

    private List<OrderItem> toOrderItems(@NotNull(message = "items must be not null") List<OrderItemDto> items) {
        return items.stream().map(item -> OrderItem.builder()
                                                   .product(Product.builder()
                                                                   .id(new ProductId(item.getProductId()))
                                                                   .price(new Money(item.getPrice()))
                                                                   .build())
                                                   .price(new Money(item.getPrice()))
                                                   .quantity(item.getQuantity())
                                                   .subTotal(new Money(item.getSubTotal()))
                                                   .build())
                    .toList();
    }
}
