package com.vn.edu.iuh.fit.cosmetics_bussiness_project.services;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ShoppingCartItem;

import java.util.List;

public interface ShoppingCartItemService {

    ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem);

    List<ShoppingCartItem> getAllShoppingCartItems();

    ShoppingCartItem getShoppingCartItemById(Long id);

    ShoppingCartItem updateShoppingCartItem(Long id, ShoppingCartItem updatedShoppingCartItem);

    void deleteShoppingCartItem(Long id);

    List<ShoppingCartItem> getShoppingCartItemsByUsername(String username);

	ShoppingCartItem createShoppingCartItem(ShoppingCartItem shoppingCartItem, Long userId);
}