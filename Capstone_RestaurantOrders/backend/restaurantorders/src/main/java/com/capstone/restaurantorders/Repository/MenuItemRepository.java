package com.capstone.restaurantorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
