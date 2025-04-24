package com.alten.alten_shop.dto;

import com.alten.alten_shop.util.InventoryStock;
import com.alten.alten_shop.entity.Product;

import java.math.BigInteger;

public record ProductRequest(
        Long id,
        String code,
        String name,
        String description,
        String image,
        String category,
        Double price,
        Integer quantity,
        String internalReference,
        Integer shellId,
        InventoryStock inventoryStatus,
        Integer rating,
        BigInteger createdAt,
        BigInteger updatedAt
) {
    public Product toEntity() {
        return new Product(id(), code(), name(), description(), image(), category(), price(), quantity(), internalReference(), shellId(), inventoryStatus(), rating(), createdAt(), updatedAt());
    }
}
