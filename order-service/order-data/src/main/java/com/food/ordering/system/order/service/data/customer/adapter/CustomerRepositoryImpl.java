package com.food.ordering.system.order.service.data.customer.adapter;

import com.food.ordering.system.domain.value.CustomerId;
import com.food.ordering.system.order.service.data.customer.repository.CustomerJpaRepository;
import com.food.ordering.system.order.service.domain.port.output.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public boolean existsById(CustomerId customerId) {
        return customerJpaRepository.existsById(customerId.getValue());
    }
}
