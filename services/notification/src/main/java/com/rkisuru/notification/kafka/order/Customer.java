package com.rkisuru.notification.kafka.order;

public record Customer(

        Integer id,
        String firstname,
        String lastname,
        String email
) {
}
