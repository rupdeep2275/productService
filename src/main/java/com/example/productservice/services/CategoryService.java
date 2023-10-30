package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;

import java.util.List;
import java.util.Optional;
public interface CategoryService {
    Optional<List<Category>> getAllCategories();

    Optional<List<Product>> getProductsInCategory(String categoryName);
}
