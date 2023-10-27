package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.value.Identity;

public abstract class AggregateRoot<T extends Identity<?>> extends Entity<T> {
}
