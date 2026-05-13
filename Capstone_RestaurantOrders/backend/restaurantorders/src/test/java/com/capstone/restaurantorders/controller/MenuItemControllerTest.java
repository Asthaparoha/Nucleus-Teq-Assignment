package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.MenuItemRequestDTO;
import com.capstone.restaurantorders.dto.MenuItemResponseDTO;
import com.capstone.restaurantorders.service.MenuItemService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MenuItemControllerTest {

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuItemController menuItemController;

    @Test
    void shouldGetByCategory() {

        when(menuItemService.getByCategory(1L))
                .thenReturn(List.of(new MenuItemResponseDTO()));

        List<MenuItemResponseDTO> result = menuItemController.getByCategory(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(menuItemService).getByCategory(1L);
    }

    @Test
    void shouldGetByRestaurant() {

        when(menuItemService.getMenuItemsByRestaurant(1L))
                .thenReturn(List.of(new MenuItemResponseDTO()));

        List<MenuItemResponseDTO> result = menuItemController.getByRestaurant(1L);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(menuItemService).getMenuItemsByRestaurant(1L);
    }

    @Test
    void shouldCreateMenuItem() {

        MenuItemRequestDTO request = new MenuItemRequestDTO();

        when(menuItemService.createMenuItem(any(MenuItemRequestDTO.class)))
                .thenReturn("Created");

        String result = menuItemController.createMenuItem(request);

        assertEquals("Created", result);

        verify(menuItemService).createMenuItem(any(MenuItemRequestDTO.class));
    }

    @Test
    void shouldUpdateMenuItem() {

        MenuItemRequestDTO request = new MenuItemRequestDTO();

        when(menuItemService.updateMenuItem(eq(1L), any(MenuItemRequestDTO.class)))
                .thenReturn("Updated");

        String result = menuItemController.updateMenuItem(1L, request);

        assertEquals("Updated", result);

        verify(menuItemService).updateMenuItem(eq(1L), any(MenuItemRequestDTO.class));
    }

    @Test
    void shouldDeleteMenuItem() {

        String result = menuItemController.deleteMenuItem(1L);

        assertEquals("Menu item deleted successfully", result);

        verify(menuItemService).deleteMenuItem(1L);
    }
}