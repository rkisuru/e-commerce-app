package com.rkisuru.order.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT orders FROM Order orders WHERE orders.customerId = :oAuth2User")
    List<Order> findOrdersByUser(String oAuth2User);

    @Query("SELECT orders FROM Order orders WHERE orders.customerId = :oAuth2User AND orders.id = :orderId")
    Order findOrderByUser(String oAuth2User, Integer orderId);
}
