package com.rkisuru.order.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestParam Integer productId, @RequestParam Double quantity, String oAuth2User) {
        try {
            OrderResponse order = orderService.createOrder(productId, quantity, oAuth2User);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(String oAuth2User) {
        List<OrderResponse> orders = orderService.getAllOrders(oAuth2User);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer orderId, String oAuth2User) {
        OrderResponse order = orderService.getOrderById(orderId, oAuth2User);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
