package com.capstone.restaurantorders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PlaceOrderRequestDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "Street is required")
    @Pattern(
            regexp = "^[A-Za-z0-9 ,.-]+$",
            message = "Street contains invalid characters"
    )
    private String street;

    @NotBlank(message = "City is required")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "City should contain only alphabets"
    )
    private String city;

    @NotBlank(message = "State is required")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "State should contain only alphabets"
    )
    private String state;

    @NotBlank(message = "Zip code is required")
    @Pattern(
            regexp = "^[0-9]{6}$",
            message = "Zip code must be exactly 6 digits"
    )
    private String zipCode;

    // ===== GETTERS & SETTERS =====

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}