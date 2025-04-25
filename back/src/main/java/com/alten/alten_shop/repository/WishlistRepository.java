package com.alten.alten_shop.repository;

import com.alten.alten_shop.entity.Product;
import com.alten.alten_shop.entity.User;
import com.alten.alten_shop.entity.Wishlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, Long> {
    List<Wishlist> findByUser(User user);
    boolean existsByUserAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);
}
