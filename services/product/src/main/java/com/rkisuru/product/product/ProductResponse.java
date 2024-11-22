package com.rkisuru.product.product;

public record ProductResponse(

        Integer id,
        String name,
        String description,
        Double stock,
        Double price,
        String category
) {
}
