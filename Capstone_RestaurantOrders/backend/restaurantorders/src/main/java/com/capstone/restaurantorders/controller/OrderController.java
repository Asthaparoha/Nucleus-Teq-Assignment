package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.PlaceOrderRequestDTO;
import com.capstone.restaurantorders.service.OrderService;
import org.springframework.web.bind.annotation.*;
import com.capstone.restaurantorders.dto.OrderResponseDTO;
import java.util.List;
/**
 * Controller for handling order-related APIs
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    /**
     * PLACE ORDER (Customer)
     * Endpoint: POST /api/orders/place
     */
    @GetMapping("/all")
    public List<OrderResponseDTO> getAllOrders() {
        return orderService.getAllOrders();
    }
    @PostMapping("/place")
    public String placeOrder(@RequestBody PlaceOrderRequestDTO request) {
        return orderService.placeOrder(request);
    }

    /**
     * UPDATE ORDER STATUS (Restaurant Owner)
     * Endpoint: PATCH /api/orders/updateStatus/{orderId}?status=PENDING
     */

    @GetMapping("/user/{userId}")
    public List<OrderResponseDTO> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }
    @DeleteMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "Order cancelled successfully";
    }
    @PutMapping("/updateStatus/{orderId}")
    public String updateStatus(@PathVariable Long orderId,
                               @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return "Order status updated successfully";
    }
}