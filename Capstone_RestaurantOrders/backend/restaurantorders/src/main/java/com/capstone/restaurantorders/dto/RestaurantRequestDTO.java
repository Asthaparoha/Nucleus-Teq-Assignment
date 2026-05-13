package com.capstone.restaurantorders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RestaurantRequestDTO {

    @NotBlank(message = "Restaurant name is required")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Restaurant name should contain only alphabets"
    )
    @Size(min = 2, max = 50,
            message = "Restaurant name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 200,
            message = "Description must be between 5 and 200 characters")
    private String description;

    @NotBlank(message = "Location is required")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])[A-Za-z0-9 ,]+$",
            message = "Location must contain valid characters and at least one alphabet"
    )
    private String location;

    // ===== GETTERS & SETTERS =====

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}