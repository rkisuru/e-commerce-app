package com.rkisuru.notification.notifiaction;

import com.rkisuru.notification.kafka.order.OrderConfirmation;
import com.rkisuru.notification.kafka.payment.PaymentConfirmation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private NotificationType type;
    private LocalDateTime notificationDate;

    @Embedded
    private OrderConfirmation orderConfirmation;

    @Embedded
    private PaymentConfirmation paymentConfirmation;
}
