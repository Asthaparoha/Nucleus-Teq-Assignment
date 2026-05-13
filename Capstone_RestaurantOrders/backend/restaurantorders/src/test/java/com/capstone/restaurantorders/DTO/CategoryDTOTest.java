package com.capstone.restaurantorders.DTO;

import com.capstone.restaurantorders.dto.CategoryRequestDTO;
import com.capstone.restaurantorders.dto.CategoryResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryDTOTest {

    @Test
    void shouldSetAndGetCategoryRequest() {

        CategoryRequestDTO dto = new CategoryRequestDTO();

        dto.setName("Pizza");
        dto.setRestaurantId(1L);

        assertEquals("Pizza", dto.getName());
        assertEquals(1L, dto.getRestaurantId());
    }

    @Test
    void shouldSetAndGetCategoryResponse() {

        CategoryResponseDTO dto = new CategoryResponseDTO();

        dto.setId(1L);
        dto.setName("Burger");
        dto.setRestaurantId(2L);

        assertEquals(1L, dto.getId());
        assertEquals("Burger", dto.getName());
        assertEquals(2L, dto.getRestaurantId());
    }
}