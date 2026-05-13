package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.MenuItemRequestDTO;
import com.capstone.restaurantorders.entity.*;
import com.capstone.restaurantorders.repository.*;

import com.capstone.restaurantorders.service.impl.MenuItemServiceImpl;
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
public class MenuItemServiceImplTest {

    @Mock private MenuItemRepository menuItemRepository;
    @Mock private CategoryRepository categoryRepository;
    @Mock private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuItemServiceImpl service;

    @Test
    void shouldGetMenuItemsByRestaurant() {

        MenuItem item = new MenuItem();
        item.setName("Pizza");
        item.setPrice(200.0);

        when(menuItemRepository.findByRestaurantId(1L))
                .thenReturn(List.of(item));

        assertFalse(service.getMenuItemsByRestaurant(1L).isEmpty());
    }

    @Test
    void shouldThrowWhenNoMenuItems() {

        when(menuItemRepository.findByRestaurantId(1L))
                .thenReturn(List.of());

        assertThrows(RuntimeException.class,
                () -> service.getMenuItemsByRestaurant(1L));
    }
}
