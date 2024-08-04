package com.rkisuru.order.payment;

import com.rkisuru.order.customer.CustomerResponse;
import com.rkisuru.order.order.Paymentmethod;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,
        Paymentmethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
