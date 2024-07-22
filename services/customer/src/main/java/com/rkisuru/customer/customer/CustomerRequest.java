package com.rkisuru.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Firstname is required")
        String firstname,
        @NotNull(message = "Lastname is required")
        String lastname,
        @NotNull(message = "Email is required")
        @Email(message = "Email is not a valid email address")
        String email,
        Address address
) {
}
