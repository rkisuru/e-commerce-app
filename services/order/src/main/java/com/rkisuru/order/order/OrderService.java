package com.rkisuru.order.order;

import com.rkisuru.order.customer.CustomerClient;
import com.rkisuru.order.exception.BusinessException;
import com.rkisuru.order.kafka.OrderConfirmation;
import com.rkisuru.order.kafka.OrderProducer;
import com.rkisuru.order.orderline.OrderLineRequest;
import com.rkisuru.order.orderline.OrderLineService;
import com.rkisuru.order.payment.PaymentClient;
import com.rkisuru.order.payment.PaymentRequest;
import com.rkisuru.order.product.ProductClient;
import com.rkisuru.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {

        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));

        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.products());

        var order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest: orderRequest.products()) {

            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentmethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentmethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {

        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {

        return orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException("Order not found for this id: " + id));
    }
}
