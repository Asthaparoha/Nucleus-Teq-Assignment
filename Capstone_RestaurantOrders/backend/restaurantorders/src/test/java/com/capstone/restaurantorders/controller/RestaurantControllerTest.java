package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.RestaurantRequestDTO;
import com.capstone.restaurantorders.dto.RestaurantResponseDTO;
import com.capstone.restaurantorders.service.RestaurantService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @Test
    void shouldCreateRestaurant() {
        RestaurantRequestDTO request = new RestaurantRequestDTO();

        when(restaurantService.createRestaurant(any(RestaurantRequestDTO.class)))
                .thenReturn(new RestaurantResponseDTO());

        RestaurantResponseDTO result = restaurantController.createRestaurant(request);

        assertNotNull(result);
        verify(restaurantService).createRestaurant(any(RestaurantRequestDTO.class));
    }

    @Test
    void shouldGetAllRestaurants() {
        when(restaurantService.getAllRestaurants())
                .thenReturn(List.of(new RestaurantResponseDTO()));

        List<RestaurantResponseDTO> result = restaurantController.getAllRestaurants();

        assertFalse(result.isEmpty());
        verify(restaurantService).getAllRestaurants();
    }

    @Test
    void shouldGetRestaurantById() {
        when(restaurantService.getRestaurantById(1L))
                .thenReturn(new RestaurantResponseDTO());

        RestaurantResponseDTO result = restaurantController.getRestaurantById(1L);

        assertNotNull(result);
        verify(restaurantService).getRestaurantById(1L);
    }

    @Test
    void shouldUpdateRestaurant() {
        RestaurantRequestDTO request = new RestaurantRequestDTO();

        when(restaurantService.updateRestaurant(1L, request))
                .thenReturn(new RestaurantResponseDTO());

        RestaurantResponseDTO result = restaurantController.updateRestaurant(1L, request);

        assertNotNull(result);
        verify(restaurantService).updateRestaurant(1L, request);
    }

    @Test
    void shouldDeleteRestaurant() {
        String result = restaurantController.deleteRestaurant(1L);

        assertEquals("Restaurant deleted successfully", result);
        verify(restaurantService).deleteRestaurant(1L);
    }
}