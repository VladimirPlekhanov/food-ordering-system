package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.order.service.domain.AggregateRoot;
import com.food.ordering.system.order.service.domain.valueObjects.RestaurantId;

import java.util.List;

public class Restaurant extends AggregateRoot<RestaurantId> {

    private final boolean available;
    private final List<Product> products;

    private Restaurant(Builder builder) {
        id = builder.id;
        available = builder.available;
        products = builder.products;
    }

    public boolean isAvailable() {
        return available;
    }

    public List<Product> getProducts() {
        return products;
    }

    public static final class Builder {
        private RestaurantId id;
        private boolean available;
        private List<Product> products;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(RestaurantId val) {
            id = val;
            return this;
        }

        public Builder available(boolean val) {
            available = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(this);
        }
    }
}
