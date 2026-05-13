package com.capstone.restaurantorders.DTO;

import com.capstone.restaurantorders.dto.UserRequestDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRequestDTOTest {

    @Test
    void shouldSetAllFields() {

        UserRequestDTO dto = new UserRequestDTO();

        dto.setFirstName("Astha");
        dto.setLastName("Paroha");
        dto.setEmail("test@mail.com");
        dto.setPassword("123");
        dto.setPhoneNumber("9999999999");
        dto.setStreet("Street");
        dto.setCity("City");
        dto.setState("State");
        dto.setZipCode("123456");
        dto.setRole("USER");

        assertEquals("Astha", dto.getFirstName());
        assertEquals("Paroha", dto.getLastName());
        assertEquals("test@mail.com", dto.getEmail());
        assertEquals("USER", dto.getRole());
    }
}