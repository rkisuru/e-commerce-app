package com.rkisuru.order.cart;

import com.rkisuru.order.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    @ElementCollection
    private List<Integer> products = new ArrayList<>();

    public void addProduct(Integer productId) {
        this.products.add(productId);
    }
}

