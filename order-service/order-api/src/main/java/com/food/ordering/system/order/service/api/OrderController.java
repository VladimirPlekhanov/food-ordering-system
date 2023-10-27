package com.food.ordering.system.order.service.api;

import com.food.ordering.system.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.service.domain.port.input.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    @PostMapping
    public CreateOrderResponse createOrderResponse(@RequestBody CreateOrderCommand command) {
        return orderApplicationService.createOrder(command);
    }

    @GetMapping("/{orderId}")
    public TrackOrderResponse trackOrder(@PathVariable UUID orderId) {
        return orderApplicationService.trackOrder(new TrackOrderQuery(orderId));
    }
}
