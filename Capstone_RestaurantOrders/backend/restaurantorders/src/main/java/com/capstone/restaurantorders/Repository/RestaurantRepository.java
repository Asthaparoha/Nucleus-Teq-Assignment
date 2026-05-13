package com.capstone.restaurantorders.repository;

import com.capstone.restaurantorders.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByActiveTrue();

    List<Restaurant> findByOwnerId(Long ownerId);
}