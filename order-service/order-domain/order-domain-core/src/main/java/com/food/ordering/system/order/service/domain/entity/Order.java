package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.value.*;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.order.service.domain.value.OrderItemId;
import com.food.ordering.system.order.service.domain.value.StreetAddress;
import com.food.ordering.system.order.service.domain.value.TrackingId;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Order extends AggregateRoot<OrderId> {
    private final RestaurantId restaurantId;
    private final StreetAddress deliveryAddress;
    private final Money totalPrice;
    private final List<OrderItem> items;
    private final CustomerId customerId;
    private TrackingId trackingId;
    private OrderStatus status;
    private List<String> failureMessages;

    private Order(Builder builder) {
        id = builder.id;
        restaurantId = builder.restaurantId;
        deliveryAddress = builder.deliveryAddress;
        totalPrice = builder.totalPrice;
        items = builder.items;
        customerId = builder.customerId;
        trackingId = builder.trackingId;
        status = builder.status;
        failureMessages = builder.failureMessage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void updateProductsNameAndPrice(List<Product> products) {
        var productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));
        items.forEach(item -> {
            var product = productMap.get(item.getProduct().getId());
            if (Objects.nonNull(product)) {
                item.getProduct().updateWithConfirmedNameAndPrice(product.getName(), product.getPrice());
            }
        });
    }

    public void validateOrder() throws OrderDomainException {
        validateInitialState();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void initialize() {
        id = new OrderId(UUID.randomUUID());
        trackingId = new TrackingId(UUID.randomUUID());
        status = OrderStatus.PENDING;
        initializeOrderItems(id);
    }

    public void pay() {
        if (status == OrderStatus.PENDING) {
            status = OrderStatus.PAID;
        } else {
            throw new OrderDomainException("Status should be 'PENDING', but it is " + status);
        }
    }

    public void approve() {
        if (status == OrderStatus.PAID) {
            status = OrderStatus.APPROVED;
        } else {
            throw new OrderDomainException("Status should be 'PAID', but it is " + status);
        }
    }

    public void initCancel(List<String> failureMessages) {
        if (status == OrderStatus.PAID) {
            status = OrderStatus.CANCELLING;
            updateFailureMessage(failureMessages);
        } else {
            throw new OrderDomainException("Status should be 'PAID', but it is " + status);
        }
    }

    public void cancel(List<String> failureMessages) {
        if (status == OrderStatus.CANCELLING || status == OrderStatus.PENDING) {
            status = OrderStatus.CANCELLED;
            updateFailureMessage(failureMessages);
        } else {
            throw new OrderDomainException("Status should be 'CANCELLING' or 'PENDING', but it is " + status);
        }
    }

    private void updateFailureMessage(List<String> failureMessages) {
        if (failureMessages.isEmpty()) {
            this.failureMessages = List.of();
        } else {
            this.failureMessages.addAll(failureMessages);
        }
    }

    private void validateInitialState() {
        if (id != null || status != null) {
            throw new OrderDomainException("Initial order should has id and status with null value");
        }
    }

    private void validateTotalPrice() {
        if (totalPrice == null || !totalPrice.greaterThanZero()) {
            throw new OrderDomainException("Initial order should has totalPrice with null value and should be greater than zero");
        }
    }

    private void validateItemsPrice() {
        var itemsTotal = items.stream()
                .map(item -> {
                    validateItemPrice(item);
                    return item.getSubTotal();
                }).reduce(Money.ZERO, Money::add);
        if (!itemsTotal.equals(totalPrice)) {
            throw new OrderDomainException("Total price should be valid");
        }
    }

    private void initializeOrderItems(OrderId orderId) {
        int counter = 1;
        for (OrderItem item : items) {
            item.initialize(orderId, new OrderItemId(counter++));
        }
    }

    private void validateItemPrice(OrderItem item) {
        if (!item.isValidPrice()) {
            throw new OrderDomainException("Order item price invalid for product " + item.getProduct().getId());
        }
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public StreetAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public static final class Builder {
        private OrderId id;
        private RestaurantId restaurantId;
        private StreetAddress deliveryAddress;
        private Money totalPrice;
        private List<OrderItem> items;
        private CustomerId customerId;
        private TrackingId trackingId;
        private OrderStatus status;
        private List<String> failureMessage;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder totalPrice(Money val) {
            totalPrice = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder status(OrderStatus val) {
            status = val;
            return this;
        }

        public Builder failureMessage(List<String> val) {
            failureMessage = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
