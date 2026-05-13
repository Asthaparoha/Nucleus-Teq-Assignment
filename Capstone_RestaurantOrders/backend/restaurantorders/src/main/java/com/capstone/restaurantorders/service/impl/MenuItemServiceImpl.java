package com.capstone.restaurantorders.service.impl;

import com.capstone.restaurantorders.dto.MenuItemRequestDTO;
import com.capstone.restaurantorders.dto.MenuItemResponseDTO;
import com.capstone.restaurantorders.entity.Category;
import com.capstone.restaurantorders.entity.MenuItem;
import com.capstone.restaurantorders.entity.Restaurant;
import com.capstone.restaurantorders.repository.CategoryRepository;
import com.capstone.restaurantorders.repository.MenuItemRepository;
import com.capstone.restaurantorders.repository.RestaurantRepository;
import com.capstone.restaurantorders.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation responsible for managing menu items.
 * Provides operations for creating, updating, deleting, and retrieving menu items.
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    /**
     * Constructor-based dependency injection for repositories.
     *
     * @param categoryRepository   repository for category entity
     * @param restaurantRepository repository for restaurant entity
     * @param menuItemRepository   repository for menu item entity
     */
    public MenuItemServiceImpl(CategoryRepository categoryRepository,
                               RestaurantRepository restaurantRepository,
                               MenuItemRepository menuItemRepository) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Fetches all menu items associated with a specific restaurant.
     *
     * @param restaurantId ID of the restaurant
     * @return list of menu item response DTOs
     */
    @Override
    public List<MenuItemResponseDTO> getMenuItemsByRestaurant(Long restaurantId) {

        List<MenuItem> items = menuItemRepository.findByRestaurantId(restaurantId);

        if (items.isEmpty()) {
            throw new RuntimeException("No menu items found for this restaurant");
        }

        return items.stream().map(item -> {
            MenuItemResponseDTO dto = new MenuItemResponseDTO();
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setPrice(item.getPrice());
            return dto;
        }).toList();
    }

    /**
     * Fetches menu items filtered by category.
     *
     * @param categoryId ID of the category
     * @return list of menu item response DTOs
     */
    @Override
    public List<MenuItemResponseDTO> getByCategory(Long categoryId) {

        List<MenuItem> items = menuItemRepository.findByCategoryId(categoryId);

        return items.stream().map(item -> {
            MenuItemResponseDTO dto = new MenuItemResponseDTO();
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setPrice(item.getPrice());
            dto.setCategoryId(item.getCategory().getId());
            return dto;
        }).toList();
    }

    /**
     * Creates a new menu item for a given category and restaurant.
     *
     * @param request request DTO containing menu item details
     * @return success message
     */
    @Override
    public String createMenuItem(MenuItemRequestDTO request) {

        MenuItem item = new MenuItem();
        item.setName(request.getName());
        item.setPrice(request.getPrice());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        item.setCategory(category);

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        item.setRestaurant(restaurant);

        menuItemRepository.save(item);

        return "Menu item created successfully";
    }

    /**
     * Updates an existing menu item with new details.
     *
     * @param id      ID of the menu item to update
     * @param request request DTO containing updated details
     * @return success message
     */
    @Override
    public String updateMenuItem(Long id, MenuItemRequestDTO request) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setName(request.getName());
        item.setPrice(request.getPrice());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        item.setCategory(category);

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        item.setRestaurant(restaurant);

        menuItemRepository.save(item);

        return "Menu item updated successfully";
    }

    /**
     * Deletes a menu item by its ID.
     *
     * @param id ID of the menu item to delete
     */
    @Override
    public void deleteMenuItem(Long id) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        menuItemRepository.delete(item);
    }
}