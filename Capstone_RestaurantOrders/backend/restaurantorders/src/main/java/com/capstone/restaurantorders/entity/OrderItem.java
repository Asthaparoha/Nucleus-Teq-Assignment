package com.capstone.restaurantorders.entity;

import jakarta.persistence.*;

/**
 * Entity representing individual items inside an order.
 */
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Order reference
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Menu item reference
    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    // Quantity of item
    @Column(nullable = false)
    private int quantity;

    // Price snapshot (important for history)
    @Column(nullable = false)
    private double price;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}