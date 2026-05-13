package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.MenuItemRequestDTO;
import com.capstone.restaurantorders.dto.MenuItemResponseDTO;

import java.util.List;

public interface MenuItemService {

    List<MenuItemResponseDTO> getByCategory(Long categoryId);

    List<MenuItemResponseDTO> getMenuItemsByRestaurant(Long restaurantId);
    String createMenuItem(MenuItemRequestDTO request);

    String updateMenuItem(Long id, MenuItemRequestDTO request);

    void deleteMenuItem(Long id);
}