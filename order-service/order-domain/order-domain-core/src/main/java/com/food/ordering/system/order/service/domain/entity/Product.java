package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.ProductId;

public class Product extends AggregateRoot<ProductId> {

    private String name;
    private Money price;

    public Product(ProductId id) {
        this.id = id;
    }

    private Product(Builder builder) {
        id = builder.id;
        name = builder.name;
        price = builder.price;
    }

    public Product(ProductId id, Money price) {
        this.id = id;
        this.price = price;
    }
    public static Builder builder() {
        return new Builder();
    }

    void updateWithConfirmedNameAndPrice(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public static final class Builder {
        private ProductId id;
        private String name;
        private Money price;

        private Builder() {
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
