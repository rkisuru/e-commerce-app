package com.rkisuru.order.order;

import com.rkisuru.order.customer.Customer;
import com.rkisuru.order.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse{

        private Integer id;
        private Product product;
        private String customer;
        private Double totalAmount;
        private LocalDateTime createdDate;
}
