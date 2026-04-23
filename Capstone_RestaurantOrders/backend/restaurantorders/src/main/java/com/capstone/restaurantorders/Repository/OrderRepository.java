package com.capstone.restaurantorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}