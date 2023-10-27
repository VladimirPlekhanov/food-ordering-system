package com.food.ordering.system.domain.value;

import java.util.Objects;

public abstract class Identity<T> {

    protected final T value;

    protected Identity(T value) {
        this.value = Objects.requireNonNull(value, "Id value must be not null");
    }
    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity<?> identity = (Identity<?>) o;
        return Objects.equals(value, identity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
