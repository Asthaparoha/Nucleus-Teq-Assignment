package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.AddToCartRequestDTO;
import com.capstone.restaurantorders.dto.CartResponseDTO;

public interface CartService {

    CartResponseDTO addToCart(AddToCartRequestDTO request);
    CartResponseDTO getCart(Long userId);
    void clearCart(Long userId);
    void removeItem(Long cartItemId);
}