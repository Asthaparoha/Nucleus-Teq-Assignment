package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.LoginResponseDTO;
import com.capstone.restaurantorders.dto.UserRequestDTO;
import com.capstone.restaurantorders.dto.UserResponseDTO;
import com.capstone.restaurantorders.dto.LoginRequestDTO;
import com.capstone.restaurantorders.service.UserService;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public UserResponseDTO registerUser(@Valid @RequestBody UserRequestDTO request) {
        return userService.registerUser(request);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return userService.loginUser(request);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
