package com.alten.alten_shop.service;

import com.alten.alten_shop.entity.Cart;
import com.alten.alten_shop.entity.CartItem;
import com.alten.alten_shop.entity.Product;
import com.alten.alten_shop.entity.User;
import com.alten.alten_shop.repository.CartRepository;
import com.alten.alten_shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart cart = new Cart();
            user.setPassword("hidden");
            cart.setUser(user);
            return cartRepository.save(cart);
        });
    }

    public Cart addProductToCart(User user, Long productId, Integer quantity) {
        Cart cart = getCartByUser(user);
        Product product = productRepository.findById(productId).orElseThrow();

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        Integer productQuantity = Optional.of(quantity).orElse(1);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + productQuantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(productQuantity);
            cart.getItems().add(newItem);
        }
        cart.getUser().setPassword("hidden");
        return cartRepository.save(cart);
    }

    public void removeProductFromCart(User user, Long productId) {
        Cart cart = getCartByUser(user);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        cartRepository.save(cart);
    }

    public void clearCart(User user) {
        Cart cart = getCartByUser(user);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
