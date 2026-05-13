package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.RestaurantRequestDTO;
import com.capstone.restaurantorders.dto.RestaurantResponseDTO;
import com.capstone.restaurantorders.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public RestaurantResponseDTO createRestaurant(
            @Valid @RequestBody RestaurantRequestDTO request) {

        return restaurantService.createRestaurant(request);
    }

    //  GET ALL
    @GetMapping
    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    //  GET BY ID
    @GetMapping("/{id}")
    public RestaurantResponseDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public RestaurantResponseDTO updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantRequestDTO request) {

        return restaurantService.updateRestaurant(id, request);
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public String deleteRestaurant(@PathVariable Long id) {

        restaurantService.deleteRestaurant(id);
        return "Restaurant deleted successfully";
    }
}