package com.capstone.restaurantorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
