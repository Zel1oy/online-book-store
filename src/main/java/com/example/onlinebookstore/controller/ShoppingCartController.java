package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.order.CartItemDto;
import com.example.onlinebookstore.dto.order.CartItemUpdateRequestDto;
import com.example.onlinebookstore.dto.order.CreateCartItemRequestDto;
import com.example.onlinebookstore.dto.order.ShoppingCartDto;
import com.example.onlinebookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart API",
        description = "Controller to perform actions on user's shopping cart")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Get a shopping cart",
            description = "Retrieve the user's shopping cart from DB")
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @Operation(summary = "Add new item to a cart",
            description = "Save a new item to the user's shopping cart through DB")
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ShoppingCartDto addToCart(@RequestBody @Valid CreateCartItemRequestDto request) {
        return shoppingCartService.addToCart(request);
    }

    @Operation(summary = "Update a cart item",
            description = "Update an existing cart item in the user's shopping cart by its ID")
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cart-items/{cartItemId}")
    public CartItemDto updateCartItem(@PathVariable Long cartItemId,
                                      @RequestBody @Valid CartItemUpdateRequestDto request) {
        return shoppingCartService.updateCartItem(cartItemId, request);
    }

    @Operation(summary = "Delete a cart item",
            description = "Delete the item from the user's shopping cart in DB by its ID")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{cartItemId}")
    public void deleteCartItem(@PathVariable Long cartItemId) {
        shoppingCartService.deleteCartItem(cartItemId);
    }
}
