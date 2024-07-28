package com.rkisuru.product.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull(message = "Product is mandatory")
        Integer productId,

        @NotNull(message = "Stock is mandatory")
        Double stock
) {
}
