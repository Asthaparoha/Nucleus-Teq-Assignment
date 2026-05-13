package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.RestaurantRequestDTO;
import com.capstone.restaurantorders.dto.RestaurantResponseDTO;
import java.util.List;



public interface RestaurantService {
    List<RestaurantResponseDTO> getAllRestaurants();
    RestaurantResponseDTO createRestaurant(RestaurantRequestDTO request);
    RestaurantResponseDTO getRestaurantById(Long id);
    RestaurantResponseDTO updateRestaurant(Long id, RestaurantRequestDTO request);

    void deleteRestaurant(Long id);
}