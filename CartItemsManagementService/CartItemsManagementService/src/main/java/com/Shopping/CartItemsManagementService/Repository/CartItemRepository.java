package com.Shopping.CartItemsManagementService.Repository;


import com.Shopping.CartItemsManagementService.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart_UserId(Long userId); // Ensuring it is fetching CartItems based on the userId in Cart.
}

