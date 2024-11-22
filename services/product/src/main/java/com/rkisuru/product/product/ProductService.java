package com.rkisuru.product.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {

        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public ProductResponse findById(Integer productId) {

        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product not found"));
    }

    public List<ProductResponse> findAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse editProduct(Integer productId, ProductRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new EntityNotFoundException("Product not found"));

        if(!request.name().isBlank()) {
            product.setName(request.name());
        }
        if(!request.description().isBlank()) {
            product.setDescription(request.description());
        }
        if(request.stock() != null) {
            product.setStock(request.stock());
        }
        if(request.price() != null) {
            product.setPrice(request.price());
        }
        if (!request.category().isBlank()) {
            product.setCategory(request.category());
        }
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
