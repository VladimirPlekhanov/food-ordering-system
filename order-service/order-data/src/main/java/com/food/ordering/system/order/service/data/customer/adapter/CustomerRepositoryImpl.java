package com.food.ordering.system.order.service.data.customer.adapter;

import com.food.ordering.system.domain.value.CustomerId;
import com.food.ordering.system.order.service.data.customer.repository.CustomerJpaRepository;
import com.food.ordering.system.service.domain.port.output.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public boolean existById(CustomerId customerId) {
        return customerJpaRepository.existsById(customerId.getValue());
    }
}
