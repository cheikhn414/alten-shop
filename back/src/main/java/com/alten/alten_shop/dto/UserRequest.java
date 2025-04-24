package com.alten.alten_shop.dto;

import com.alten.alten_shop.entity.User;

public record UserRequest(
        String username,
        String firstname,
        String email,
        String password) {

    public User toEntity() {
        return new User(username(), firstname(), email(), password());
    }
}
