package com.alten.alten_shop.service;

import com.alten.alten_shop.dto.UserRequest;
import com.alten.alten_shop.dto.UserResponse;
import com.alten.alten_shop.entity.User;
import com.alten.alten_shop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(UserRequest userRequest) {
        User user = userRequest.toEntity();
        user.setPassword(user.getPassword());

        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getUsername(), savedUser.getFirstname(), savedUser.getEmail());
    }
}
