package com.capstone.restaurantorders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.restaurantorders.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // A-grade custom method
    Optional<User> findByEmail(String email);
}