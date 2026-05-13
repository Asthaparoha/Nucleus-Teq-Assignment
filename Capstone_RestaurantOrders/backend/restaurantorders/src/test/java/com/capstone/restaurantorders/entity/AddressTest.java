package com.capstone.restaurantorders.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    void shouldSetAndGetValues() {

        Address address = new Address("Street", "City", "State", "12345");

        assertEquals("Street", address.getStreet());
        assertEquals("City", address.getCity());
        assertEquals("State", address.getState());
        assertEquals("12345", address.getZipCode());
    }

    @Test
    void shouldCheckEqualsAndHashCode() {

        Address a1 = new Address("S", "C", "ST", "123");
        Address a2 = new Address("S", "C", "ST", "123");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }
}