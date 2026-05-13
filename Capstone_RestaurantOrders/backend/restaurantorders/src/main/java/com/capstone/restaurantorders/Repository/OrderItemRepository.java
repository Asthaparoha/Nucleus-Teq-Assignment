package com.capstone.restaurantorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}