package com.alten.alten_shop.controller;

import com.alten.alten_shop.dto.CartRequest;
import com.alten.alten_shop.entity.Cart;
import com.alten.alten_shop.entity.User;
import com.alten.alten_shop.service.CartService;
import com.alten.alten_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService; // pour récupérer l'utilisateur courant

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        User user = userService.getCurrentUser();
        Cart cart = cartService.getCartByUser(user);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(@Valid @RequestBody CartRequest cartRequest) {
        User user = userService.getCurrentUser(); // méthode à implémenter
        Cart cart = cartService.addProductToCart(user, cartRequest.productId(), cartRequest.quantity());
        cart.getUser().setPassword(null);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long productId) {
        User user = userService.getCurrentUser();
        cartService.removeProductFromCart(user, productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/clear")
    public ResponseEntity<?> clearCart() {
        User user = userService.getCurrentUser();
        cartService.clearCart(user);
        return ResponseEntity.ok().build();
    }
}
