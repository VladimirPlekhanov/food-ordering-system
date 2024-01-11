package com.food.ordering.system.service.domain.port.input.impl;

import com.food.ordering.system.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.service.domain.port.input.PaymentResponseListener;
import com.food.ordering.system.service.domain.saga.PaymentSaga;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class PaymentResponseListenerImpl implements PaymentResponseListener {

    private final PaymentSaga paymentSaga;

    @Override
    public void process(PaymentResponse response) {
        switch (response.paymentStatus()) {
            case COMPLETED -> paymentSaga.proceed(response);
            case CANCELLED, FAILED -> paymentSaga.rollback(response);
            default -> log.warn("Unsupported payment status: {}", response.paymentStatus());
        }
    }
}
