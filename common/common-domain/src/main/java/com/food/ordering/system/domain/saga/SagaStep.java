package com.food.ordering.system.domain.saga;

public interface SagaStep<T> {

    void proceed(T data);
    void rollback(T data);
}
