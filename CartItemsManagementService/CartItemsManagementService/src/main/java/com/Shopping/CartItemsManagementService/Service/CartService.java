package com.Shopping.CartItemsManagementService.Service;

import com.Shopping.CartItemsManagementService.Entity.CartItem;
import com.Shopping.CartItemsManagementService.Repository.CartItemRepository;
import com.Shopping.CartItemsManagementService.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem addToCart(CartItem item) {
        return cartItemRepository.save(item);
    }

    public void removeFromCart(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepository.findByCart_UserId(userId);
    }

    public void clearCart(Long userId) {
        List<CartItem> items = cartItemRepository.findByCart_UserId(userId);
        if (!items.isEmpty()) {
            cartItemRepository.deleteAll(items);
        }
    }


}