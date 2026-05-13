package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.OrderResponseDTO;
import com.capstone.restaurantorders.dto.PlaceOrderRequestDTO;

import java.util.List;

public interface OrderService {

    String placeOrder(PlaceOrderRequestDTO request);

    void updateOrderStatus(Long orderId, String status);

    List<OrderResponseDTO> getUserOrders(Long userId);
    void cancelOrder(Long orderId);

    List<OrderResponseDTO> getAllOrders();
}