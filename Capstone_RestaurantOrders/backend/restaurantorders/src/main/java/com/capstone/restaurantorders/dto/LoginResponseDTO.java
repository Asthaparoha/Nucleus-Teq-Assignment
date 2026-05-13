package com.capstone.restaurantorders.dto;

public class LoginResponseDTO {

    private String token;
    private String role;
    private Long id;

    // Default constructor
    public LoginResponseDTO() {}

    // Getters & Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
