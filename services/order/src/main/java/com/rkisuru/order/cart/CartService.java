package com.rkisuru.order.cart;

import com.rkisuru.order.client.ProductClient;
import com.rkisuru.order.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductClient productClient;

    public Cart addItemsToCart(Integer productId, String oAuth2User) {

        Cart cart = cartRepository.findByUser(oAuth2User);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(oAuth2User);
        }
        Product product = productClient.getProductById(productId);
        cart.addProduct(productId);
        return cartRepository.save(cart);
    }

    public CartResponse getCart(String oAuth2User) {
        Cart cart = cartRepository.findByUser(oAuth2User);
        return CartResponse.builder()
                .userId(cart.getUserId())
                .products(
                        cart.getProducts()
                                .stream()
                                .map(productClient::getProductById)
                                .toList()
                )
                .build();
    }

    public void clearCart(String oAuth2User) {
        Cart cart = cartRepository.findByUser(oAuth2User);
        cart.getProducts().clear();
        cartRepository.save(cart);
    }

    public void removeItemFromCart(Integer productId, String oAuth2User) {
        Cart cart = cartRepository.findByUser(oAuth2User);
        cart.getProducts().remove(productId);
        cartRepository.save(cart);
    }
}
