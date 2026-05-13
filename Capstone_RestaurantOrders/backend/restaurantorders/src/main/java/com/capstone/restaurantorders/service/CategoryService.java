package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.CategoryRequestDTO;
import com.capstone.restaurantorders.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO request);

    List<CategoryResponseDTO> getCategoriesByRestaurant(Long restaurantId);

    void deleteCategory(Long id);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request);
}
