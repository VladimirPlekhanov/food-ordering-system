package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.valueObjects.Identity;

public abstract class AggregateRoot<T extends Identity<?>> extends Entity<T> {
}
