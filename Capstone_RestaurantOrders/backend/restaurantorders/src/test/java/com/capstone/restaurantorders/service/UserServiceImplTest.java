package com.capstone.restaurantorders.service;

import com.capstone.restaurantorders.dto.*;
import com.capstone.restaurantorders.entity.User;
import com.capstone.restaurantorders.repository.UserRepository;
import com.capstone.restaurantorders.security.JwtUtil;

import com.capstone.restaurantorders.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private JwtUtil jwtUtil;
    @Mock private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void shouldRegisterUser() {

        UserRequestDTO request = new UserRequestDTO();
        request.setEmail("test@mail.com");
        request.setPassword("123");
        request.setRole("USER");

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("encoded");
        when(userRepository.save(any())).thenReturn(new User());

        assertNotNull(service.registerUser(request));
    }

    @Test
    void shouldLoginUser() {

        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail("test@mail.com");
        request.setPassword("123");

        User user = new User();
        user.setPassword("encoded");

        when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);
        when(jwtUtil.generateToken(any())).thenReturn("token");

        assertNotNull(service.loginUser(request));
    }
}