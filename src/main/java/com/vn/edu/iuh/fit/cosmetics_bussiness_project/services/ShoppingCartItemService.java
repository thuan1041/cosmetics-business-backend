package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ShoppingCartItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.User;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.Product;

import java.util.List;

public interface ShoppingCartItemService {

//    ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem);
//
//    List<ShoppingCartItem> getAllShoppingCartItems();
//
//    ShoppingCartItem getShoppingCartItemById(Long id);
//
//    ShoppingCartItem updateShoppingCartItem(Long id, ShoppingCartItem updatedShoppingCartItem);
//
//    void deleteShoppingCartItem(Long id);
//
//    List<ShoppingCartItem> getShoppingCartItemsByUsername(String username);
//
//	ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem, Long userId);
//	
//	
//    List<ShoppingCartItem> getCartItems(String string);
//    ShoppingCartItem addCartItem(User user, Long productId, int quantity);
//    void removeCartItem(Long cartItemId);
//    void clearCart(User user);
    ShoppingCartItem updateShoppingCartItem(Long id, ShoppingCartItem shoppingCartItem);
    void deleteShoppingCartItem(Long id);
    List<ShoppingCartItem> getShoppingCartItemsByUsername(String username);
    ShoppingCartItem createShoppingCartItem(int quantity, String userId, Long productId);
}