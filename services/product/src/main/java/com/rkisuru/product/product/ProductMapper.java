package com.rkisuru.product.product;

import com.rkisuru.product.category.Category;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {


    public Product toProduct(ProductRequest productRequest) {

        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .stock(productRequest.stock())
                .price(productRequest.price())
                .category(Category.valueOf(productRequest.category()))
                .subCategory(Category.SubCategory.valueOf(productRequest.subCategory()))
                .build();
    }

    public ProductResponse toProductResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getStock(),
                product.getPrice(),
                product.getCategory(),
                product.getSubCategory()
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
