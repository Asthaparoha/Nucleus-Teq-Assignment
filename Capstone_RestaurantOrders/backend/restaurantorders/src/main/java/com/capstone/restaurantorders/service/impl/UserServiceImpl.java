package com.capstone.restaurantorders.service.impl;

import com.capstone.restaurantorders.dto.*;
import com.capstone.restaurantorders.entity.Address;
import com.capstone.restaurantorders.entity.User;
import com.capstone.restaurantorders.repository.UserRepository;
import com.capstone.restaurantorders.security.JwtUtil;
import com.capstone.restaurantorders.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔴 REGISTER
    @Override
    public UserResponseDTO registerUser(UserRequestDTO request) {

        // Duplicate email check
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole("CUSTOMER");

        Address address = new Address(
                request.getStreet(),
                request.getCity(),
                request.getState(),
                request.getZipCode()
        );
        user.setAddress(address);

        User savedUser = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setEmail(savedUser.getEmail());
        response.setWalletBalance(savedUser.getWalletBalance());

        return response;
    }

    // 🔴 LOGIN
    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDTO(token);
    }
}