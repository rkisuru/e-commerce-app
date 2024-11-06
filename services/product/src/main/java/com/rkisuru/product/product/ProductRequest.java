package com.rkisuru.product.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(

        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @NotNull(message = "Stock should be positive")
        Double stock,
        @NotNull(message = "Price should be positive")
        BigDecimal price,

        String category,
        String subCategory
) {
}
