package com.rkisuru.order.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT cart FROM Cart cart WHERE cart.userId = :userId")
    Cart findByUser(String userId);
}
