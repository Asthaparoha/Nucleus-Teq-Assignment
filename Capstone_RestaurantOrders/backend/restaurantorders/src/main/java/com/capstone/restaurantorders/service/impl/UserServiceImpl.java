package com.capstone.restaurantorders.service.impl;

import com.capstone.restaurantorders.dto.*;
import com.capstone.restaurantorders.entity.Address;
import com.capstone.restaurantorders.entity.User;
import com.capstone.restaurantorders.repository.UserRepository;
import com.capstone.restaurantorders.security.JwtUtil;
import com.capstone.restaurantorders.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service implementation responsible for user-related operations.
 * Handles registration, authentication, and user retrieval.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor-based dependency injection for required components.
     *
     * @param userRepository repository for user entity
     * @param jwtUtil utility for JWT token generation
     * @param passwordEncoder encoder for securing passwords
     */
    public UserServiceImpl(UserRepository userRepository,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user in the system.
     * Initializes wallet balance and persists user details.
     *
     * @param request request DTO containing user details
     * @return response DTO with basic user information
     */
    @Override
    public UserResponseDTO registerUser(UserRequestDTO request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();

        user.setCreatedAt(LocalDateTime.now());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setPhoneNumber(request.getPhoneNumber());

        Address address = new Address(
                request.getStreet(),
                request.getCity(),
                request.getState(),
                request.getZipCode()
        );

        user.setStreet(address.getStreet());
        user.setCity(address.getCity());
        user.setState(address.getState());
        user.setZipCode(address.getZipCode());

        user.setWalletBalance(1000.0);

        User savedUser = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setFirstName(savedUser.getFirstName());
        response.setEmail(savedUser.getEmail());
        response.setWalletBalance(savedUser.getWalletBalance());

        return response;
    }

    /**
     * Authenticates a user and generates a JWT token upon successful login.
     *
     * @param request request DTO containing login credentials
     * @return response DTO containing token and user details
     */
    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setRole(user.getRole());
        response.setId(user.getId());

        return response;
    }

    /**
     * Retrieves user details by user ID.
     *
     * @param id ID of the user
     * @return response DTO containing user information
     */
    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponseDTO response = new UserResponseDTO();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setEmail(user.getEmail());
        response.setWalletBalance(user.getWalletBalance());

        return response;
    }
}