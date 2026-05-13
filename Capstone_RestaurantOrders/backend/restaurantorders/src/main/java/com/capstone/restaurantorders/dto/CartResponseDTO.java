package com.capstone.restaurantorders.dto;

import java.util.List;

public class CartResponseDTO {

    private Long userId;
    private List<CartItemDTO> items;
    private Double totalAmount;

    // ===== GETTERS & SETTERS =====

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) { 
        this.userId = userId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
