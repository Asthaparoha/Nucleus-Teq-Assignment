package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartItemTest {

    @Test
    void shouldSetValues() {

        CartItem item = new CartItem();
        Cart cart = new Cart();
        MenuItem menuItem = new MenuItem();

        item.setQuantity(2);
        item.setCart(cart);
        item.setMenuItem(menuItem);

        assertEquals(2, item.getQuantity());
        assertEquals(cart, item.getCart());
        assertEquals(menuItem, item.getMenuItem());
    }
}
