package com.rkisuru.product.product;

import jakarta.validation.constraints.NotNull;

public record ProductRequest(

        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @NotNull(message = "Stock should be positive")
        Double stock,
        @NotNull(message = "Price should be positive")
        Double price,

        String category
) {
}
