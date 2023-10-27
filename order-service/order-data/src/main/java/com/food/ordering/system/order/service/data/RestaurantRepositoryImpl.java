package com.food.ordering.system.order.service.data;

import com.food.ordering.system.domain.value.Money;
import com.food.ordering.system.domain.value.ProductId;
import com.food.ordering.system.domain.value.RestaurantId;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.service.domain.port.output.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    List<Product> products = List.of(
            Product
                    .builder()
                    .id(new ProductId(UUID.fromString("a14fdecc-a3de-4093-a2fb-3108ad9e3229")))
                    .name("ProductFirst")
                    .price(new Money(new BigDecimal("10.1")))
                    .build(),
            Product
                    .builder()
                    .id(new ProductId(UUID.fromString("e7afde67-8485-476d-b81c-50b894013d49")))
                    .name("ProductSecond")
                    .price(new Money(new BigDecimal("20.2")))
                    .build());

    Restaurant restaurant = Restaurant.builder()
                                      .id(new RestaurantId(UUID.fromString("3b3e3870-0982-4523-954e-2f067bf6adf5")))
                                      .available(true)
                                      .products(products)
                                      .build();

    @Override
    public Optional<Restaurant> find(Restaurant restaurant) {
        return restaurant.equals(this.restaurant) ? Optional.of(this.restaurant) : Optional.empty();
    }
}
