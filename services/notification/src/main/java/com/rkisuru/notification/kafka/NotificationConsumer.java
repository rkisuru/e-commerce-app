package com.rkisuru.notification.kafka;

import com.rkisuru.notification.email.EmailService;
import com.rkisuru.notification.kafka.order.OrderConfirmation;
import com.rkisuru.notification.kafka.payment.PaymentConfirmation;
import com.rkisuru.notification.notifiaction.Notification;
import com.rkisuru.notification.notifiaction.NotificationRepository;
import com.rkisuru.notification.notifiaction.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation confirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic %s", confirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(confirmation)
                        .build()
        );

        var customerName = confirmation.customerFirstname()+" "+confirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                confirmation.customerEmail(),
                customerName,
                confirmation.amount(),
                confirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation confirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic %s", confirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(confirmation)
                        .build()
        );

        var customerName = confirmation.customer().firstname()+" "+confirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                confirmation.customer().email(),
                customerName,
                confirmation.totalAmount(),
                confirmation.orderReference(),
                confirmation.products()
        );
    }
}
