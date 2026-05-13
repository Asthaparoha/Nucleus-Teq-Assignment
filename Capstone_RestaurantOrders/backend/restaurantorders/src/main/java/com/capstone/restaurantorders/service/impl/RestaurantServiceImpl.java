package com.capstone.restaurantorders.service.impl;

import com.capstone.restaurantorders.dto.RestaurantRequestDTO;
import com.capstone.restaurantorders.dto.RestaurantResponseDTO;
import com.capstone.restaurantorders.entity.Restaurant;
import com.capstone.restaurantorders.repository.RestaurantRepository;
import com.capstone.restaurantorders.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation responsible for managing restaurant-related operations.
 * Handles creation, retrieval, updating, and deletion of restaurant entities.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * Constructor-based dependency injection for repository.
     *
     * @param restaurantRepository repository for restaurant entity
     */
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Creates a new restaurant using the provided request data.
     *
     * @param request request DTO containing restaurant details
     * @return response DTO representing the created restaurant
     */
    @Override
    public RestaurantResponseDTO createRestaurant(RestaurantRequestDTO request) {

        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setLocation(request.getLocation());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        RestaurantResponseDTO response = new RestaurantResponseDTO();
        response.setId(savedRestaurant.getId());
        response.setName(savedRestaurant.getName());
        response.setDescription(savedRestaurant.getDescription());
        response.setLocation(savedRestaurant.getLocation());
        response.setActive(savedRestaurant.isActive());
        response.setCreatedAt(savedRestaurant.getCreatedAt());

        return response;
    }

    /**
     * Retrieves all restaurants available in the system.
     *
     * @return list of restaurant response DTOs
     */
    @Override
    public List<RestaurantResponseDTO> getAllRestaurants() {

        return restaurantRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific restaurant by its ID.
     *
     * @param id ID of the restaurant
     * @return response DTO of the restaurant
     */
    @Override
    public RestaurantResponseDTO getRestaurantById(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        return mapToResponse(restaurant);
    }

    /**
     * Updates an existing restaurant with new details.
     *
     * @param id      ID of the restaurant to update
     * @param request request DTO containing updated data
     * @return updated restaurant response DTO
     */
    @Override
    public RestaurantResponseDTO updateRestaurant(Long id, RestaurantRequestDTO request) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setLocation(request.getLocation());

        Restaurant updated = restaurantRepository.save(restaurant);

        return mapToResponse(updated);
    }

    /**
     * Deletes a restaurant by its ID.
     *
     * @param id ID of the restaurant to delete
     */
    @Override
    public void deleteRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        try {
            restaurantRepository.delete(restaurant);
        }
        catch (Exception e) {
            throw new RuntimeException(
                    "Cannot delete restaurant because categories or menu items exist"
            );
        }
    }
    /**
     * Converts a restaurant entity into a response DTO.
     *
     * @param restaurant restaurant entity
     * @return mapped response DTO
     */
    private RestaurantResponseDTO mapToResponse(Restaurant restaurant) {

        RestaurantResponseDTO response = new RestaurantResponseDTO();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setDescription(restaurant.getDescription());
        response.setLocation(restaurant.getLocation());
        response.setActive(restaurant.isActive());
        response.setCreatedAt(restaurant.getCreatedAt());

        return response;
    }
}