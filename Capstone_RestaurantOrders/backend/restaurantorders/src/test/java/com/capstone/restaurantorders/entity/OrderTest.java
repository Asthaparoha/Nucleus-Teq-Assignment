package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void shouldSetValues() {

        Order order = new Order();
        User user = new User();

        order.setUser(user);
        order.setTotalAmount(500.0);
        order.setStreet("Street");
        order.setCity("City");
        order.setState("State");
        order.setZipCode("12345");
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());

        assertEquals(user, order.getUser());
        assertEquals(500.0, order.getTotalAmount());
        assertEquals(OrderStatus.PLACED, order.getStatus());
    }
}
