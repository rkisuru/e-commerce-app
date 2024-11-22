package com.rkisuru.order.order;

import com.rkisuru.order.client.ProductClient;
import com.rkisuru.order.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public Product findProductById(Integer productId) {
        return productClient.getProductById(productId);
    }

    public OrderResponse createOrder(Integer productId, Double quantity, String oAuth2User) throws Exception {

        Product product = productClient.getProductById(productId);

        if (product.getStock() < quantity) {
            throw new Exception("Insufficient stock available");
        }

        Order order = new Order();
        order.setProductId(productId);
        order.setCustomerId(oAuth2User);
        order.setTotalAmount(totalPrice(quantity, product.getPrice()));
        order.setCreatedDate(LocalDateTime.now());

        orderRepository.save(order);
        return OrderResponse.builder()
                .id(order.getId())
                .product(findProductById(order.getProductId()))
                .customer(order.getCustomerId())
                .totalAmount(order.getTotalAmount())
                .createdDate(order.getCreatedDate())
                .build();
    }

    public List<OrderResponse> getAllOrders(String oAuth2User) {
        return orderRepository.findOrdersByUser(oAuth2User)
                .stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .product(findProductById(order.getProductId()))
                        .customer(order.getCustomerId())
                        .totalAmount(order.getTotalAmount())
                        .createdDate(order.getCreatedDate())
                        .build())
                .toList();
    }

    public OrderResponse getOrderById(Integer orderId, String oAuth2User) {
        Order order = orderRepository.findOrderByUser(oAuth2User, orderId);
        return OrderResponse.builder()
                .id(order.getId())
                .product(findProductById(order.getProductId()))
                .customer(order.getCustomerId())
                .totalAmount(order.getTotalAmount())
                .createdDate(order.getCreatedDate())
                .build();
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    public Double totalPrice(Double quantity, Double price) {
        return quantity * price;
    }
}
