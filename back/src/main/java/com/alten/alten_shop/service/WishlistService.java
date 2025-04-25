package com.alten.alten_shop.service;

import com.alten.alten_shop.entity.Product;
import com.alten.alten_shop.entity.User;
import com.alten.alten_shop.entity.Wishlist;
import com.alten.alten_shop.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserService userService;

    public WishlistService(WishlistRepository wishlistRepository, UserService userService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
    }

    public List<Wishlist> getWishlist() {
        return wishlistRepository.findByUser(userService.getCurrentUser());
    }

    public Wishlist addToWishlist(Product product) {
        User user = userService.getCurrentUser();
        if (wishlistRepository.existsByUserAndProduct(user, product)) {
            throw new IllegalStateException("Product already in wishlist");
        }
        Wishlist item = new Wishlist();
        item.setUser(user);
        item.setProduct(product);
        item.setCreatedAt(LocalDateTime.now());
        return wishlistRepository.save(item);
    }

    public void removeFromWishlist(Product product) {
        wishlistRepository.deleteByUserAndProduct(userService.getCurrentUser(), product);
    }
}
