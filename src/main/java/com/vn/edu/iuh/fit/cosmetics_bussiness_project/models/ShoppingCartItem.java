package com.vn.edu.iuh.fit.cosmetics_bussiness_project.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shopping_cart_item")
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    // Getters and setters
}