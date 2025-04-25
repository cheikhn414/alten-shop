package com.alten.alten_shop.dto;

import com.alten.alten_shop.util.InventoryStock;
import com.alten.alten_shop.entity.Product;

public record ProductRequest(
        String code,
        String name,
        String description,
        String image,
        String category,
        Integer price,
        Integer quantity,
        String internalReference,
        Integer shellId,
        InventoryStock inventoryStatus,
        Integer rating
) {
    public Product toEntity() {
        return new Product(code(), name(), description(), image(), category(), price(), 0, internalReference(), shellId(), inventoryStatus(), rating());
    }
}
