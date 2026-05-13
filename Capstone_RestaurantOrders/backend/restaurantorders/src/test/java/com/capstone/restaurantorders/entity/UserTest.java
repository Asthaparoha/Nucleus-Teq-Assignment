package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void shouldSetValues() {

        User user = new User();

        user.setFirstName("Astha");
        user.setLastName("Paroha");
        user.setEmail("test@mail.com");
        user.setPassword("123");
        user.setRole("USER");
        user.setWalletBalance(1000.0);
        user.setCreatedAt(LocalDateTime.now());

        assertEquals("Astha", user.getFirstName());
        assertEquals("USER", user.getRole());
        assertEquals(1000.0, user.getWalletBalance());
    }
}