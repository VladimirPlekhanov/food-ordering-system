package com.food.ordering.system.order.service.data;

import com.food.ordering.system.domain.value.CustomerId;
import com.food.ordering.system.service.domain.port.output.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public boolean existById(CustomerId customerId) {
        return true;
    }
}
