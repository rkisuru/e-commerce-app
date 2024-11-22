package com.rkisuru.order.cart;

import com.rkisuru.order.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    private Integer id;
    private String userId;
    private List<Product> products;
}
