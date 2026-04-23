package com.capstone.restaurantorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
