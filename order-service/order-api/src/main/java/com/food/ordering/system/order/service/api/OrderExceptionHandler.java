package com.food.ordering.system.order.service.api;

import com.food.ordering.system.order.service.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class OrderExceptionHandler {

    @ExceptionHandler(value = OrderDomainException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(OrderDomainException exception) {
        log.warn(exception.getMessage(), exception);
        return new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage());
    }
}
