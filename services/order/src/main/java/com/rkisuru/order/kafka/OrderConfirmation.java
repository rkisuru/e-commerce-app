package com.rkisuru.order.kafka;

import com.rkisuru.order.customer.CustomerResponse;
import com.rkisuru.order.order.Paymentmethod;
import com.rkisuru.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String orderReference,
        BigDecimal totalAmount,
        Paymentmethod paymentmethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
