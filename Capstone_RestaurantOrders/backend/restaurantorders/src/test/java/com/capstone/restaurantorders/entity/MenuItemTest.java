package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {

    @Test
    void shouldSetValues() {

        MenuItem item = new MenuItem();
        Category category = new Category();
        Restaurant restaurant = new Restaurant();

        item.setName("Burger");
        item.setPrice(150.0);
        item.setCategory(category);
        item.setRestaurant(restaurant);

        assertEquals("Burger", item.getName());
        assertEquals(150.0, item.getPrice());
        assertEquals(category, item.getCategory());
        assertEquals(restaurant, item.getRestaurant());
    }
}
