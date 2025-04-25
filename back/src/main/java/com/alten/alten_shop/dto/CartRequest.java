package com.alten.alten_shop.dto;

import jakarta.validation.constraints.NotNull;

public record CartRequest(
        @NotNull Long productId,
        Integer quantity) {
}
