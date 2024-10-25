package com.rkisuru.order.customer;

public record CustomerResponse(

        Integer id,
        String firstname,
        String lastname,
        String email
) {
}
