package com.rkisuru.order.order;

import java.math.BigDecimal;

public record OrderResponse(

        Integer id,
        String reference,
        BigDecimal amount,
        Paymentmethod paymentmethod,
        Integer customerId
) {
}
