package com.capstone.restaurantorders.service.impl;

import com.capstone.restaurantorders.dto.AddToCartRequestDTO;
import com.capstone.restaurantorders.dto.CartItemDTO;
import com.capstone.restaurantorders.dto.CartResponseDTO;
import com.capstone.restaurantorders.entity.*;
import com.capstone.restaurantorders.repository.CartRepository;
import com.capstone.restaurantorders.repository.CartItemRepository;
import com.capstone.restaurantorders.repository.UserRepository;
import com.capstone.restaurantorders.repository.MenuItemRepository;
import com.capstone.restaurantorders.service.CartService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for cart operations
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    // Constructor Injection
    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           UserRepository userRepository,
                           MenuItemRepository menuItemRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
    }

    /**
     * Adds item to cart
     */
    @Override
    public CartResponseDTO addToCart(AddToCartRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MenuItem menuItem = menuItemRepository.findById(request.getMenuItemId())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElse(null);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(request.getQuantity());

        cartItemRepository.save(cartItem);

        return getCart(user.getId());
    }

    /**
     * Get cart details
     */
    @Override
    public CartResponseDTO getCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        List<CartItemDTO> itemDTOList = new ArrayList<>();
        double total = 0;

        for (CartItem item : items) {

            CartItemDTO dto = new CartItemDTO();
            dto.setCartItemId(item.getId());
            dto.setMenuItemId(item.getMenuItem().getId());
            dto.setItemName(item.getMenuItem().getName());
            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getMenuItem().getPrice());

            total += item.getMenuItem().getPrice() * item.getQuantity();

            itemDTOList.add(dto);
        }

        CartResponseDTO response = new CartResponseDTO();
        response.setItems(itemDTOList);
        response.setTotalAmount(total);

        return response;
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());
        cartItemRepository.deleteAll(items);
    }

    @Override
    public void removeItem(Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cartItemRepository.delete(item);
    }
}