package com.capstone.restaurantorders.entity;

import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ONE user → ONE cart
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructor
    public Cart() {}

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}