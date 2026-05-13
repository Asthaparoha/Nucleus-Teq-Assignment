package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderItemTest {

    @Test
    void shouldSetValues() {

        OrderItem item = new OrderItem();
        Order order = new Order();
        MenuItem menuItem = new MenuItem();

        item.setQuantity(3);
        item.setOrder(order);
        item.setMenuItem(menuItem);

        assertEquals(3, item.getQuantity());
        assertEquals(order, item.getOrder());
        assertEquals(menuItem, item.getMenuItem());
    }
}