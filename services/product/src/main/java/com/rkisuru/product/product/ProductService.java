package com.rkisuru.product.product;

import com.rkisuru.product.category.Category;
import com.rkisuru.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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

    @Transactional(rollbackOn = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {

        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }
        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i=0; i< storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getStock() < productRequest.stock()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with Id::"+ productRequest.productId());
            }
            var currentStock = product.getStock() - productRequest.stock();
            product.setStock(currentStock);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.stock()));
        }
        return purchasedProducts;

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
        if (request.category() != null) {
            product.setCategory(Category.valueOf(request.category()));
        }
        if (request.subCategory() != null) {
            product.setSubCategory(Category.SubCategory.valueOf(request.subCategory()));
        }
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
