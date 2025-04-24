package com.alten.alten_shop.dto;

import com.alten.alten_shop.entity.User;

public record UserResponse(
        String username,
        String firstname,
        String email) {

    public UserResponse toDto(User userEntity) {
        return new UserResponse(userEntity.getUsername(), userEntity.getFirstname(), userEntity.getEmail());
    }
}
