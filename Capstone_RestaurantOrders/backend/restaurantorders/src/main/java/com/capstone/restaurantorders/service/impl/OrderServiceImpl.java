package com.capstone.restaurantorders.service.impl;

import com.capstone.restaurantorders.dto.OrderItemDTO;
import com.capstone.restaurantorders.dto.OrderResponseDTO;
import com.capstone.restaurantorders.dto.PlaceOrderRequestDTO;
import com.capstone.restaurantorders.entity.*;
import com.capstone.restaurantorders.repository.*;
import com.capstone.restaurantorders.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation responsible for handling order lifecycle operations.
 * Provides functionalities such as placing orders, updating order status,
 * retrieving order history, and handling cancellations with business rules.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;

    /**
     * Constructor-based dependency injection for repositories.
     *
     * @param orderRepository      repository for order entity
     * @param userRepository       repository for user entity
     * @param cartRepository       repository for cart entity
     * @param cartItemRepository   repository for cart item entity
     * @param orderItemRepository  repository for order item entity
     */
    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Places a new order for a user by converting cart items into order items.
     * Deducts wallet balance and clears the cart after successful order placement.
     *
     * @param request request DTO containing user and address details
     * @return confirmation message with order ID
     */
    @Override
    public String placeOrder(PlaceOrderRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();

        if (user.getWalletBalance() < totalAmount) {
            throw new RuntimeException("Insufficient balance");
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());

        order.setStreet(user.getStreet());
        order.setCity(user.getCity());
        order.setState(user.getState());
        order.setZipCode(user.getZipCode());

        Order savedOrder = orderRepository.save(order);

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setMenuItem(item.getMenuItem());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getMenuItem().getPrice());

            orderItemRepository.save(orderItem);
        }

        user.setWalletBalance(user.getWalletBalance() - totalAmount);
        userRepository.save(user);

        cartItemRepository.deleteAll(cartItems);

        return "Order placed successfully with ID: " + savedOrder.getId();
    }

    /**
     * Updates the status of an existing order.
     *
     * @param orderId ID of the order
     * @param status  new status value
     */
    @Override
    public void updateOrderStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        try {
            OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value: " + status);
        }

        orderRepository.save(order);
    }

    /**
     * Retrieves all orders placed by a specific user.
     *
     * @param userId ID of the user
     * @return list of order response DTOs
     */
    @Override
    public List<OrderResponseDTO> getUserOrders(Long userId) {

        List<Order> orders = orderRepository.findByUserId(userId);

        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found");
        }

        return orders.stream().map(order -> {

            OrderResponseDTO dto = new OrderResponseDTO();
            dto.setOrderId(order.getId());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setStatus(order.getStatus().name());
            dto.setCreatedAt(order.getCreatedAt().toString());

            List<OrderItemDTO> items = order.getOrderItems().stream().map(item -> {
                OrderItemDTO itemDTO = new OrderItemDTO();
                itemDTO.setItemName(item.getMenuItem().getName());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setPrice(item.getMenuItem().getPrice());
                return itemDTO;
            }).toList();

            dto.setItems(items);

            return dto;

        }).toList();
    }

    /**
     * Cancels an order within a limited time window and refunds the user wallet.
     *
     * @param orderId ID of the order to cancel
     */
    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus().name().equals("CANCELLED")) {
            throw new RuntimeException("Order already cancelled");
        }

        LocalDateTime createdTime = order.getCreatedAt();
        LocalDateTime now = LocalDateTime.now();

        long seconds = java.time.Duration.between(createdTime, now).getSeconds();

        if (seconds > 30) {
            throw new RuntimeException("Cannot cancel after 30 seconds");
        }

        User user = order.getUser();
        user.setWalletBalance(user.getWalletBalance() + order.getTotalAmount());

        order.setStatus(OrderStatus.CANCELLED);

        userRepository.save(user);
        orderRepository.save(order);
    }

    /**
     * Retrieves all orders in the system for administrative purposes.
     *
     * @return list of order response DTOs
     */
    @Override
    public List<OrderResponseDTO> getAllOrders() {

        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {

            OrderResponseDTO dto = new OrderResponseDTO();
            dto.setOrderId(order.getId());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setStatus(order.getStatus().name());
            dto.setCreatedAt(order.getCreatedAt().toString());

            return dto;

        }).toList();
    }
}