package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.MenuItemRequestDTO;
import com.capstone.restaurantorders.dto.MenuItemResponseDTO;
import com.capstone.restaurantorders.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;

    /**
     * Constructor-based dependency injection for MenuItemService.
     *
     * @param menuItemService service layer for menu item operations
     */
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    // GET BY CATEGORY
    @GetMapping("/category/{categoryId}")
    public List<MenuItemResponseDTO> getByCategory(@PathVariable Long categoryId) {
        return menuItemService.getByCategory(categoryId);
    }

    // GET BY RESTAURANT
    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItemResponseDTO> getByRestaurant(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurant(restaurantId);
    }

    // CREATE
    @PostMapping
    public String createMenuItem( @Valid @RequestBody MenuItemRequestDTO request) {
        return menuItemService.createMenuItem(request);
    }

    // UPDATE
    @PutMapping("/{id}")
    public String updateMenuItem(@PathVariable Long id,
                                 @RequestBody MenuItemRequestDTO request) {
        return menuItemService.updateMenuItem(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return "Menu item deleted successfully";
    }

}