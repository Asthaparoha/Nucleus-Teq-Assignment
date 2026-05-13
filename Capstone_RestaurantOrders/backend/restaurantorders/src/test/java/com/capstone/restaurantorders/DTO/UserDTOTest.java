package com.capstone.restaurantorders.DTO;

import com.capstone.restaurantorders.dto.UserRequestDTO;
import com.capstone.restaurantorders.dto.UserResponseDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {

    @Test
    void shouldSetAndGetRequestDTO() {

        UserRequestDTO dto = new UserRequestDTO();

        dto.setFirstName("Astha");
        dto.setLastName("Paroha");
        dto.setEmail("test@mail.com");
        dto.setPassword("123");
        dto.setRole("USER");

        assertEquals("Astha", dto.getFirstName());
        assertEquals("Paroha", dto.getLastName());
        assertEquals("test@mail.com", dto.getEmail());
        assertEquals("USER", dto.getRole());
    }

    @Test
    void shouldSetAndGetResponseDTO() {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(1L);
        dto.setFirstName("Astha");
        dto.setEmail("test@mail.com");
        dto.setWalletBalance(1000.0);

        assertEquals(1L, dto.getId());
        assertEquals("Astha", dto.getFirstName());
        assertEquals(1000.0, dto.getWalletBalance());
    }
}