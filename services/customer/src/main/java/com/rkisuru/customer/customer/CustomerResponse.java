package com.rkisuru.customer.customer;

public record CustomerResponse(

        Integer id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
