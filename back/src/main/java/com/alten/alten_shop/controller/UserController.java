package com.alten.alten_shop.controller;

import com.alten.alten_shop.dto.UserRequest;
import com.alten.alten_shop.dto.UserResponse;
import com.alten.alten_shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse register(@Valid @RequestBody UserRequest userRequest) {
        return userService.register(userRequest);
    }
}
