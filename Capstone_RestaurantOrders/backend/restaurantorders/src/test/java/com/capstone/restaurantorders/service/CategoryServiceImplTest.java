package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.CategoryRequestDTO;
import com.capstone.restaurantorders.entity.*;
import com.capstone.restaurantorders.repository.*;

import com.capstone.restaurantorders.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock private CategoryRepository categoryRepository;
    @Mock private RestaurantRepository restaurantRepository;

    @InjectMocks
    private CategoryServiceImpl service;

    @Test
    void shouldCreateCategory() {

        CategoryRequestDTO request = new CategoryRequestDTO();
        request.setName("Pizza");
        request.setRestaurantId(1L);

        Restaurant restaurant = new Restaurant();

        Category savedCategory = new Category();
        savedCategory.setName("Pizza");
        savedCategory.setRestaurant(restaurant);

        when(restaurantRepository.findById(1L))
                .thenReturn(Optional.of(restaurant));

        when(categoryRepository.save(any()))
                .thenReturn(savedCategory);

        assertNotNull(service.createCategory(request));
    }
}