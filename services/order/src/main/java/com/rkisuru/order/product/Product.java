package com.rkisuru.order.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private String category;
}
