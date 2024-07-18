package com.Shopping.CartItemsManagementService.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Assuming one cart per user, add this field and annotate appropriately if needed
    @Column(unique = true)
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
    private Set<CartItem> items = new HashSet<>();
}

