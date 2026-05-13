package com.capstone.restaurantorders.DTO;

import com.capstone.restaurantorders.dto.PlaceOrderRequestDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaceOrderRequestDTOTest {

    @Test
    void shouldSetAndGetUserId() {

        PlaceOrderRequestDTO dto = new PlaceOrderRequestDTO();

        dto.setUserId(5L);

        assertEquals(5L, dto.getUserId());
    }
}