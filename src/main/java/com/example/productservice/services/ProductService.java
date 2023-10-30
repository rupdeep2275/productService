package com.example.productservice.services;

import com.example.productservice.models.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<Product>> getAllProducts();

    Optional<Product> getSingleProduct(Long productId);

    Optional<Product> addNewProduct(Product product);

    Optional<Product> updateProduct(Long productId, Product product);

    Optional<Product> replaceProduct(Long productId, Product product);

    Optional<Product> deleteProduct(Long productId);
}
