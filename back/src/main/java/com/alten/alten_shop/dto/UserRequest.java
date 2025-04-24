package com.alten.alten_shop.dto;

import com.alten.alten_shop.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank String username,
        @NotBlank String firstname,
        @Email @NotBlank String email,
        @NotBlank String password) {

    public User toEntity() {
        return new User(username(), firstname(), email(), password());
    }
}
