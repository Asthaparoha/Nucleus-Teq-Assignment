package com.capstone.restaurantorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}