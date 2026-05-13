package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

    @Test
    void shouldSetValues() {

        Restaurant r = new Restaurant();

        r.setName("Cafe");
        r.setDescription("Nice");
        r.setLocation("Delhi");
        r.setActive(true);

        assertEquals("Cafe", r.getName());
        assertTrue(r.isActive());
    }

    @Test
    void shouldSetCreatedAtOnPrePersist() {

        Restaurant r = new Restaurant();
        r.prePersist();

        assertNotNull(r.getCreatedAt());
    }
}
