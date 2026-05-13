package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.RestaurantRequestDTO;
import com.capstone.restaurantorders.entity.Restaurant;
import com.capstone.restaurantorders.repository.RestaurantRepository;

import com.capstone.restaurantorders.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {

    @Mock private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl service;

    @Test
    void shouldCreateRestaurant() {

        RestaurantRequestDTO request = new RestaurantRequestDTO();
        request.setName("Cafe");

        when(restaurantRepository.save(any()))
                .thenReturn(new Restaurant());

        assertNotNull(service.createRestaurant(request));
    }

    @Test
    void shouldGetAllRestaurants() {

        when(restaurantRepository.findAll())
                .thenReturn(List.of(new Restaurant()));

        assertFalse(service.getAllRestaurants().isEmpty());
    }
}