package com.rkisuru.product.product;

import java.math.BigDecimal;

public record ProductResponse(

        Integer id,
        String name,
        String description,
        Double stock,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription

) {
}
