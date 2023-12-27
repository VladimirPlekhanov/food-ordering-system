package com.food.ordering.system.service.domain.port.input;

import com.food.ordering.system.service.domain.dto.create.CreateOrderDto;
import com.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderDto command);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery query);
}
