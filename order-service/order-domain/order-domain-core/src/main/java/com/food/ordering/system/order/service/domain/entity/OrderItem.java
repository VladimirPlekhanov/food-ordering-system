package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.Entity;
import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.OrderId;
import com.food.ordering.system.order.service.domain.value.OrderItemId;

public class OrderItem extends Entity<OrderItemId> {

    private OrderId orderId;
    private final Product product;
    private final Money price;
    private final Integer quantity;
    private final Money subTotal;

    private OrderItem(Builder builder) {
        id = builder.id;
        orderId = builder.orderId;
        product = builder.product;
        price = builder.price;
        quantity = builder.quantity;
        subTotal = builder.subTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    void initialize(OrderId orderId, OrderItemId orderItemId) {
        this.id = orderItemId;
        this.orderId = orderId;
    }

    boolean isValidPrice() {
        return price.greaterThanZero() && price.equals(product.getPrice()) && subTotal.equals(price.multiply(quantity));
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public Money getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public static final class Builder {
        private OrderItemId id;
        private OrderId orderId;
        private Product product;
        private Money price;
        private Integer quantity;
        private Money subTotal;

        private Builder() {
        }

        public Builder id(OrderItemId val) {
            id = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
