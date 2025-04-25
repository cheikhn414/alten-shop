package com.alten.alten_shop.controller;

import com.alten.alten_shop.entity.Product;
import com.alten.alten_shop.entity.Wishlist;
import com.alten.alten_shop.repository.ProductRepository;
import com.alten.alten_shop.service.WishlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    private final ProductRepository productRepository;

    public WishlistController(WishlistService wishlistService, ProductRepository productRepository) {
        this.wishlistService = wishlistService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Wishlist> getWishlist() {
        return wishlistService.getWishlist();
    }

    @PostMapping("/{productId}")
    public Wishlist add(@PathVariable Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return wishlistService.addToWishlist(product);
    }

    @DeleteMapping("/{productId}")
    public void remove(@PathVariable Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        wishlistService.removeFromWishlist(product);
    }
}
