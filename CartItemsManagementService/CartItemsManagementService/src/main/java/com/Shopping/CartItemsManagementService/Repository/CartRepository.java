package com.Shopping.CartItemsManagementService.Repository;
import com.Shopping.CartItemsManagementService.Entity.Cart;
import com.Shopping.CartItemsManagementService.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId); // Adjust if you're querying for carts specifically by a user identifier.

//    List<Cart> findByUserId(Long userId); // Adjust if you're querying for carts specifically by a user identifier.
//    // Using a derived query through the Cart entity's relationship
//    List<CartItem> findByCart_UserId(Long userId);
//
//    void deleteAll(List<CartItem> items);
//
//    List<CartItem> findByUserId(Long userId);

}

