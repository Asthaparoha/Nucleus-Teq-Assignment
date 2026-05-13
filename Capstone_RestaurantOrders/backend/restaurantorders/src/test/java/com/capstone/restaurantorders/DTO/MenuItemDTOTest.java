package com.capstone.restaurantorders.DTO;

import com.capstone.restaurantorders.dto.MenuItemRequestDTO;
import com.capstone.restaurantorders.dto.MenuItemResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemDTOTest {

    @Test
    void shouldSetAndGetRequestDTO() {

        MenuItemRequestDTO dto = new MenuItemRequestDTO();

        dto.setName("Pizza");
        dto.setPrice(299.0);
        dto.setCategoryId(1L);
        dto.setRestaurantId(2L);

        assertEquals("Pizza", dto.getName());
        assertEquals(299.0, dto.getPrice());
        assertEquals(1L, dto.getCategoryId());
        assertEquals(2L, dto.getRestaurantId());
    }

    @Test
    void shouldSetAndGetResponseDTO() {

        MenuItemResponseDTO dto = new MenuItemResponseDTO();

        dto.setId(1L);
        dto.setName("Burger");
        dto.setPrice(199.0);
        dto.setCategoryId(2L);

        assertEquals(1L, dto.getId());
        assertEquals("Burger", dto.getName());
        assertEquals(199.0, dto.getPrice());
        assertEquals(2L, dto.getCategoryId());
    }
}