package com.capstone.restaurantorders.DTO;

import com.capstone.restaurantorders.dto.RestaurantRequestDTO;
import com.capstone.restaurantorders.dto.RestaurantResponseDTO;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantDTOTest {

    @Test
    void shouldSetAndGetRequestDTO() {

        RestaurantRequestDTO dto = new RestaurantRequestDTO();

        dto.setName("Cafe");
        dto.setDescription("Nice place");
        dto.setLocation("Delhi");

        assertEquals("Cafe", dto.getName());
        assertEquals("Nice place", dto.getDescription());
        assertEquals("Delhi", dto.getLocation());
    }

    @Test
    void shouldSetAndGetResponseDTO() {

        RestaurantResponseDTO dto = new RestaurantResponseDTO();

        dto.setId(1L);
        dto.setName("Cafe");
        dto.setActive(true);
        dto.setCreatedAt(LocalDateTime.now());

        assertEquals(1L, dto.getId());
        assertTrue(dto.isActive());
    }
}
