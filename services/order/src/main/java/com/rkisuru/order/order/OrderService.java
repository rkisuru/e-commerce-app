package com.rkisuru.order.order;

import com.rkisuru.order.customer.CustomerClient;
import com.rkisuru.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;

    public Integer createOrder(OrderRequest orderRequest) {

        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));



        return null;
    }
}
