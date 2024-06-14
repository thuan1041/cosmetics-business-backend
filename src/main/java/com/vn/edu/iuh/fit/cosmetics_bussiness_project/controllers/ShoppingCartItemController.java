package com.vn.edu.iuh.fit.cosmetics_bussiness_project.controllers;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ShoppingCartItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shopping-cart-items")
public class ShoppingCartItemController {

    private final ShoppingCartItemService shoppingCartItemService;

    @Autowired
    public ShoppingCartItemController(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartItem>> getAllShoppingCartItems() {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllShoppingCartItems();
        return ResponseEntity.ok(shoppingCartItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartItem> getShoppingCartItemById(@PathVariable("id") Long id) {
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartItemService.getShoppingCartItemById(id);
        return shoppingCartItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ShoppingCartItem> addShoppingCartItem(@RequestBody ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem createdShoppingCartItem = shoppingCartItemService.saveShoppingCartItem(shoppingCartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShoppingCartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCartItem> updateShoppingCartItem(
            @PathVariable("id") Long id,
            @RequestBody ShoppingCartItem shoppingCartItem) {
        if (!shoppingCartItemService.getShoppingCartItemById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        shoppingCartItem.setId(id);
        ShoppingCartItem updatedShoppingCartItem = shoppingCartItemService.saveShoppingCartItem(shoppingCartItem);
        return ResponseEntity.ok(updatedShoppingCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingCartItem(@PathVariable("id") Long id) {
        if (!shoppingCartItemService.getShoppingCartItemById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        shoppingCartItemService.deleteShoppingCartItem(id);
        return ResponseEntity.noContent().build();
    }
}