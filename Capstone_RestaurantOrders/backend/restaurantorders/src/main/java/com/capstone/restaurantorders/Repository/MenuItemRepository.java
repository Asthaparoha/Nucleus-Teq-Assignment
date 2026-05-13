package com.capstone.restaurantorders.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByRestaurantId(Long restaurantId);
    List<MenuItem> findByCategoryId(Long categoryId);
    boolean existsByCategoryId(Long categoryId);
}
