package com.vn.edu.iuh.fit.cosmetics_bussiness_project.controllers;

import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ApiResponse;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.models.ShoppingCartItem;
import com.vn.edu.iuh.fit.cosmetics_bussiness_project.services.ShoppingCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-cart-items")
public class ShoppingCartItemController {

//	@Autowired
//	private ShoppingCartItemService shoppingCartItemService;
//
//	@GetMapping
//	public ResponseEntity<ApiResponse<List<ShoppingCartItem>>> getAllShoppingCartItems() {
//		List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getAllShoppingCartItems();
//		ApiResponse<List<ShoppingCartItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Success",
//				shoppingCartItems);
//		return ResponseEntity.ok(response);
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<ApiResponse<ShoppingCartItem>> getShoppingCartItemById(@PathVariable("id") Long id) {
//		ShoppingCartItem shoppingCartItem = shoppingCartItemService.getShoppingCartItemById(id);
//		if (shoppingCartItem != null) {
//			ApiResponse<ShoppingCartItem> response = new ApiResponse<>(HttpStatus.OK.value(), "Success",
//					shoppingCartItem);
//			return ResponseEntity.ok(response);
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND)
//				.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "ShoppingCartItem not found", null));
//	}
//
////    @PostMapping("/")
////    public ResponseEntity<ApiResponse<ShoppingCartItem>> createShoppingCartItem(@RequestBody ShoppingCartItem shoppingCartItem, Long userId) {
////        ShoppingCartItem createdShoppingCartItem = shoppingCartItemService.createShoppingCartItem(shoppingCartItem);
////        ApiResponse<ShoppingCartItem> response = new ApiResponse<>(HttpStatus.CREATED.value(), "ShoppingCartItem created successfully", createdShoppingCartItem);
////        return ResponseEntity.status(HttpStatus.CREATED).body(response);
////    }
//	@PostMapping("/")
//	public ResponseEntity<ApiResponse<ShoppingCartItem>> createShoppingCartItem(
//			@RequestBody ShoppingCartItem shoppingCartItem, @RequestParam("userId") Long userId) {
//		ShoppingCartItem createdShoppingCartItem = shoppingCartItemService.createShoppingCartItem(shoppingCartItem,
//				userId);
//		if (createdShoppingCartItem != null) {
//			ApiResponse<ShoppingCartItem> response = new ApiResponse<>(HttpStatus.CREATED.value(),
//					"ShoppingCartItem created successfully", createdShoppingCartItem);
//			return ResponseEntity.status(HttpStatus.CREATED).body(response);
//		} else {
//			ApiResponse<ShoppingCartItem> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
//					"Failed to create ShoppingCartItem", null);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//		}
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<ApiResponse<ShoppingCartItem>> updateShoppingCartItem(@PathVariable("id") Long id,
//			@RequestBody ShoppingCartItem shoppingCartItem) {
//		ShoppingCartItem updatedShoppingCartItem = shoppingCartItemService.updateShoppingCartItem(id, shoppingCartItem);
//		if (updatedShoppingCartItem != null) {
//			ApiResponse<ShoppingCartItem> response = new ApiResponse<>(HttpStatus.OK.value(),
//					"ShoppingCartItem updated successfully", updatedShoppingCartItem);
//			return ResponseEntity.ok(response);
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND)
//				.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "ShoppingCartItem not found", null));
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteShoppingCartItem(@PathVariable("id") Long id) {
//		shoppingCartItemService.deleteShoppingCartItem(id);
//		return ResponseEntity.noContent().build();
//	}
//
//	@GetMapping("/user/{username}")
//	public ResponseEntity<ApiResponse<List<ShoppingCartItem>>> getShoppingCartItemsByUsername(
//			@PathVariable("username") String username) {
//		List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getShoppingCartItemsByUsername(username);
//		ApiResponse<List<ShoppingCartItem>> response = new ApiResponse<>(HttpStatus.OK.value(), "Success",
//				shoppingCartItems);
//		return ResponseEntity.ok(response);
//	}
    @Autowired
    private ShoppingCartItemService shoppingCartItemService;

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShoppingCartItem>> updateShoppingCartItem(
            @PathVariable("id") Long id,
            @RequestBody ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem updatedShoppingCartItem = shoppingCartItemService.updateShoppingCartItem(id, shoppingCartItem);
        if (updatedShoppingCartItem != null) {
            ApiResponse<ShoppingCartItem> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "ShoppingCartItem updated successfully",
                    updatedShoppingCartItem
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "ShoppingCartItem not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingCartItem(@PathVariable("id") Long id) {
        shoppingCartItemService.deleteShoppingCartItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<ApiResponse<List<ShoppingCartItem>>> getShoppingCartItemsByUsername(
            @PathVariable("username") String username) {
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemService.getShoppingCartItemsByUsername(username);
        ApiResponse<List<ShoppingCartItem>> response = new ApiResponse<>(
                HttpStatus.OK.value(), 
                "Success",
                shoppingCartItems
        );
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<ShoppingCartItem>> createShoppingCartItem(@RequestBody ShoppingCartItem shoppingCartItem) {
        ShoppingCartItem createdShoppingCartItem = shoppingCartItemService.createShoppingCartItem(shoppingCartItem);
        ApiResponse<ShoppingCartItem> response = new ApiResponse<>(HttpStatus.CREATED.value(), "ShoppingCartItem created successfully", createdShoppingCartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}