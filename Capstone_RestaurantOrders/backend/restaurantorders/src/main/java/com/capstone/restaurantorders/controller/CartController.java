package com.capstone.restaurantorders.controller;

import com.capstone.restaurantorders.dto.AddToCartRequestDTO;
import com.capstone.restaurantorders.dto.CartResponseDTO;
import com.capstone.restaurantorders.service.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartResponseDTO addToCart(@Valid @RequestBody AddToCartRequestDTO request) {
        return cartService.addToCart(request);
    }
    // VIEW CART
    @GetMapping("/{userId}")
    public CartResponseDTO viewCart(@PathVariable Long userId) {
        return cartService.getCart(userId);
    }

    // REMOVE ITEM
    @DeleteMapping("/item/{cartItemId}")
    public String removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return "Item removed from cart";
    }
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared";
    }
}