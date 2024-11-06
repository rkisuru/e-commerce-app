package com.rkisuru.product.product;

import com.rkisuru.product.category.Category;

import java.math.BigDecimal;

public record ProductResponse(

        Integer id,
        String name,
        String description,
        Double stock,
        BigDecimal price,
        Category category,
        Category.SubCategory subCategory

) {
}
