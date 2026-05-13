package com.capstone.restaurantorders.DTO;

import com.capstone.restaurantorders.dto.CartItemDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartItemDTOTest {

    @Test
    void shouldSetAndGetValues() {

        CartItemDTO dto = new CartItemDTO();

        dto.setMenuItemId(1L);
        dto.setItemName("Pizza");
        dto.setQuantity(2);
        dto.setPrice(250.0);

        assertEquals(1L, dto.getMenuItemId());
        assertEquals("Pizza", dto.getItemName());
        assertEquals(2, dto.getQuantity());
        assertEquals(250.0, dto.getPrice());
    }
}