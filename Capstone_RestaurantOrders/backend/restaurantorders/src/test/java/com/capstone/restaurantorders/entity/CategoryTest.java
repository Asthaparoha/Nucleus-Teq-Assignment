package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    void shouldSetValues() {

        Category category = new Category();
        Restaurant restaurant = new Restaurant();

        category.setName("Pizza");
        category.setRestaurant(restaurant);

        assertEquals("Pizza", category.getName());
        assertEquals(restaurant, category.getRestaurant());
    }
}