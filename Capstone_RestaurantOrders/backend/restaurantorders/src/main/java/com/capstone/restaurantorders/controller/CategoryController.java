package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.CategoryRequestDTO;
import com.capstone.restaurantorders.dto.CategoryResponseDTO;
import com.capstone.restaurantorders.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // CREATE CATEGORY
    @PostMapping
    public CategoryResponseDTO createCategory(
            @Valid @RequestBody CategoryRequestDTO request) {

        return categoryService.createCategory(request);
    }

    // GET BY RESTAURANT
    @GetMapping("/restaurant/{restaurantId}")
    public List<CategoryResponseDTO> getByRestaurant(
            @PathVariable Long restaurantId) {

        return categoryService.getCategoriesByRestaurant(restaurantId);
    }
    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO request) {

        return categoryService.updateCategory(id, request);
    }
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "Category deleted successfully";
    }
}
