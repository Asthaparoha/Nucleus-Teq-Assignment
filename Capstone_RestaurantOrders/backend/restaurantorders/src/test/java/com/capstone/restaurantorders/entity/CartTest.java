package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    void shouldSetUser() {

        Cart cart = new Cart();
        User user = new User();

        cart.setUser(user);

        assertEquals(user, cart.getUser());
    }
}