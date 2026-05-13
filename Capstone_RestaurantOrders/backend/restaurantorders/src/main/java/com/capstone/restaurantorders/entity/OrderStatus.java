package com.capstone.restaurantorders.entity;

/**
 * Enum representing the lifecycle stages of an order.
 *
 * <p>Flow:
 * PLACED → PENDING → DELIVERED → COMPLETED
 * Can be CANCELLED within allowed time.</p>
 */
public enum OrderStatus {

    /** Order is successfully placed by customer */
    PLACED,

    /** Order is accepted by restaurant and is being prepared */
    PENDING,

    /** Order has been delivered to customer */
    DELIVERED,

    /** Order lifecycle completed successfully */
    COMPLETED,

    /** Order has been cancelled (by user or restaurant) */
    CANCELLED
}