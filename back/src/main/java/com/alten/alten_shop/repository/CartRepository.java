package com.alten.alten_shop.repository;

import com.alten.alten_shop.entity.Cart;
import com.alten.alten_shop.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
