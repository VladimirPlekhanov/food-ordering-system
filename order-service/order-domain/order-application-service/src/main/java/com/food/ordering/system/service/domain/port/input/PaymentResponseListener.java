package com.food.ordering.system.service.domain.port.input;

import com.food.ordering.system.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseListener {

    void process(PaymentResponse response);
}
