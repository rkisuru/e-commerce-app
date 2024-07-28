package com.rkisuru.product.product;

import com.rkisuru.product.category.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(ProductRequest productRequest) {

        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .stock(productRequest.stock())
                .price(productRequest.price())
                .category(Category.builder().id(productRequest.id()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()

        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, Double stock) {

        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                stock
        );
    }
}
