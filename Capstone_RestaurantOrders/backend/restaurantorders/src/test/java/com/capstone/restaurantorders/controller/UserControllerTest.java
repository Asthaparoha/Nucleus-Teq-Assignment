package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.LoginRequestDTO;
import com.capstone.restaurantorders.dto.LoginResponseDTO;
import com.capstone.restaurantorders.dto.UserRequestDTO;
import com.capstone.restaurantorders.dto.UserResponseDTO;
import com.capstone.restaurantorders.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void shouldRegisterUserSuccessfully() {

        UserRequestDTO request = new UserRequestDTO();
        UserResponseDTO response = new UserResponseDTO();

        when(userService.registerUser(any(UserRequestDTO.class))).thenReturn(response);

        UserResponseDTO result = userController.registerUser(request);

        assertNotNull(result);
        verify(userService).registerUser(any(UserRequestDTO.class));
    }

    @Test
    void shouldLoginUserSuccessfully() {

        LoginRequestDTO request = new LoginRequestDTO();
        LoginResponseDTO response = new LoginResponseDTO();

        when(userService.loginUser(any(LoginRequestDTO.class))).thenReturn(response);

        LoginResponseDTO result = userController.login(request);

        assertNotNull(result);
        verify(userService).loginUser(any(LoginRequestDTO.class));
    }

    @Test
    void shouldGetUserById() {

        UserResponseDTO response = new UserResponseDTO();

        when(userService.getUserById(1L)).thenReturn(response);

        UserResponseDTO result = userController.getUser(1L);

        assertNotNull(result);
        verify(userService).getUserById(1L);
    }

    @Test
    void shouldReturnTestAPIMessage() {

        String result = userController.testAPI();

        assertEquals("Protected API working", result);
    }

    @Test
    void shouldThrowExceptionWhenLoginFails() {

        LoginRequestDTO request = new LoginRequestDTO();

        when(userService.loginUser(any(LoginRequestDTO.class)))
                .thenThrow(new RuntimeException("Invalid credentials"));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> userController.login(request)
        );

        assertEquals("Invalid credentials", ex.getMessage());
    }
}