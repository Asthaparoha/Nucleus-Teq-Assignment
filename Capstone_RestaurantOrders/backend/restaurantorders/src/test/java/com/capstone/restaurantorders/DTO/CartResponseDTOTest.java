package com.capstone.restaurantorders.DTO;

import com.capstone.restaurantorders.dto.CartItemDTO;
import com.capstone.restaurantorders.dto.CartResponseDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CartResponseDTOTest {

    @Test
    void shouldSetAndGetValues() {

        CartResponseDTO dto = new CartResponseDTO();

        CartItemDTO item = new CartItemDTO();
        item.setItemName("Burger");

        dto.setUserId(1L);
        dto.setItems(List.of(item));
        dto.setTotalAmount(500.0);

        assertEquals(1L, dto.getUserId());
        assertEquals(1, dto.getItems().size());
        assertEquals(500.0, dto.getTotalAmount());
    }
}
