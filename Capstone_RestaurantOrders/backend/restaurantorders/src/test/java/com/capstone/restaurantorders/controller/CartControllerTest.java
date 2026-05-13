package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.AddToCartRequestDTO;
import com.capstone.restaurantorders.dto.CartResponseDTO;
import com.capstone.restaurantorders.service.CartService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @Test
    void shouldAddToCart() {
        AddToCartRequestDTO request = new AddToCartRequestDTO();
        CartResponseDTO response = new CartResponseDTO();

        when(cartService.addToCart(any(AddToCartRequestDTO.class))).thenReturn(response);

        CartResponseDTO result = cartController.addToCart(request);

        assertNotNull(result);
        verify(cartService).addToCart(any(AddToCartRequestDTO.class));
    }

    @Test
    void shouldViewCart() {
        when(cartService.getCart(1L)).thenReturn(new CartResponseDTO());

        CartResponseDTO result = cartController.viewCart(1L);

        assertNotNull(result);
        verify(cartService).getCart(1L);
    }

    @Test
    void shouldRemoveItem() {
        String result = cartController.removeItem(1L);

        assertEquals("Item removed from cart", result);
        verify(cartService).removeItem(1L);
    }
}
