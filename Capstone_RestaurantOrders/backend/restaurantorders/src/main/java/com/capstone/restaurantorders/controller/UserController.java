package com.capstone.restaurantorders.controller;
import jakarta.validation.Valid;
import com.capstone.restaurantorders.dto.UserRequestDTO;
import com.capstone.restaurantorders.dto.UserResponseDTO;
import com.capstone.restaurantorders.service.UserService;
import com.capstone.restaurantorders.dto.LoginRequestDTO;
import com.capstone.restaurantorders.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST API
    @PostMapping("/register")
    public UserResponseDTO registerUser(@Valid @RequestBody UserRequestDTO request) {
        return userService.registerUser(request);
    }
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO request) {
        return userService.loginUser(request);
    }
    @GetMapping("/test")
    public String testAPI() {
        return "Protected API working";
    }
}