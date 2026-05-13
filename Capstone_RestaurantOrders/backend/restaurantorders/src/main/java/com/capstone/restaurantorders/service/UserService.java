package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.UserRequestDTO;
import com.capstone.restaurantorders.dto.UserResponseDTO;
import com.capstone.restaurantorders.dto.LoginRequestDTO;
import com.capstone.restaurantorders.dto.LoginResponseDTO;

public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO request);

    LoginResponseDTO loginUser(LoginRequestDTO request);

    UserResponseDTO getUserById(Long id);
}
