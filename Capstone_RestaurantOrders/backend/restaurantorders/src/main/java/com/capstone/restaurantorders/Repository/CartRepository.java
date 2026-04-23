package com.capstone.restaurantorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}