package com.example.cart;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}