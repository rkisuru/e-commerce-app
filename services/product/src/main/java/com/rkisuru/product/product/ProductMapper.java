package com.rkisuru.product.product;

import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(ProductRequest productRequest) {

        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .stock(productRequest.stock())
                .price(productRequest.price())
                .category(productRequest.category())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
