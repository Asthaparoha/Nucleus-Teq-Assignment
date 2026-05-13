package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.CategoryRequestDTO;
import com.capstone.restaurantorders.dto.CategoryResponseDTO;
import com.capstone.restaurantorders.service.CategoryService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void shouldCreateCategory() {
        CategoryRequestDTO request = new CategoryRequestDTO();

        when(categoryService.createCategory(any(CategoryRequestDTO.class)))
                .thenReturn(new CategoryResponseDTO());

        CategoryResponseDTO result = categoryController.createCategory(request);

        assertNotNull(result);
        verify(categoryService).createCategory(any(CategoryRequestDTO.class));
    }

    @Test
    void shouldGetCategoriesByRestaurant() {
        when(categoryService.getCategoriesByRestaurant(1L))
                .thenReturn(List.of(new CategoryResponseDTO()));

        List<CategoryResponseDTO> result = categoryController.getByRestaurant(1L);

        assertFalse(result.isEmpty());
        verify(categoryService).getCategoriesByRestaurant(1L);
    }
}