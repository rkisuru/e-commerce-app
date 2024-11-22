package com.rkisuru.order.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer productId;
    private String customerId;
    private Double totalAmount;
    private LocalDateTime createdDate;
}
