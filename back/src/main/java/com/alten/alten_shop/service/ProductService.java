package com.alten.alten_shop.service;

import com.alten.alten_shop.entity.Product;
import org.springframework.stereotype.Service;
import com.alten.alten_shop.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product save(ProductRequest productRequest) {
        Product product = productRequest.toEntity();
        return productRepository.save(product);
    }

    public Product update(Long productId, ProductRequest productRequest) {
        Product productToUpdate = findById(productId);
        if (productToUpdate != null) {
            productToUpdate.setName(productRequest.name());
            productToUpdate.setPrice(productRequest.price());
            productToUpdate.setDescription(productRequest.description());
            return productRepository.save(productToUpdate);
        }
        return null;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
