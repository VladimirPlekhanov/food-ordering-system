package com.food.ordering.system.service.domain.handler;

import com.food.ordering.system.domain.value.CustomerId;
import com.food.ordering.system.order.service.domain.OrderDomainService;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import com.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.service.domain.mapper.OrderDomainMapper;
import com.food.ordering.system.service.domain.port.output.CustomerRepository;
import com.food.ordering.system.service.domain.port.output.OrderRepository;
import com.food.ordering.system.service.domain.port.output.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderCommandHandler {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderDomainMapper orderDomainMapper;
    private final RestaurantRepository restaurantRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public CreateOrderResponse handle(CreateOrderCommand command) {
        checkCustomer(new CustomerId(command.getCustomerId()));
        final var restaurant = checkRestaurant(orderDomainMapper.toRestaurant(command));
        var order = orderDomainMapper.toOrder(command);
        orderDomainService.validateAndInitiateOrder(order, restaurant);
        orderRepository.save(order);
        log.info("Created order with id: {}", order.getId());
        return orderDomainMapper.toCreateOrderResponse(order);
    }

    void checkCustomer(CustomerId customerId) {
        if (!customerRepository.existById(customerId)) {
            log.warn("Could not find customer with id: {}", customerId);
            throw new OrderDomainException("customer not found");
        }
    }

    private Restaurant checkRestaurant(Restaurant restaurant) {
        return restaurantRepository.find(restaurant).orElseThrow(() -> {
            log.warn("Could not find restaurant with id: {}", restaurant.getId());
            return new OrderDomainException("restaurant not found");
        });
    }
}
