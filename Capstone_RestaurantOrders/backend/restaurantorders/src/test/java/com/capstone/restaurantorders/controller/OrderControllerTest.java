package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.service.OrderService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Test
    void shouldUpdateOrderStatus() {
        String result = orderController.updateStatus(1L, "PENDING");

        assertEquals("Order status updated successfully", result);
        verify(orderService).updateOrderStatus(1L, "PENDING");
    }
}