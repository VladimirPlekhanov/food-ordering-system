package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.valueObjects.Identity;

import java.util.Objects;

public abstract class Entity<T extends Identity<?>> {

    protected T id;

    public T getId() {
        return id;
    }

    protected void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s{id = %s}", getClass().getSimpleName(), id);
    }
}
