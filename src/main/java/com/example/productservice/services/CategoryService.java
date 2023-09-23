package com.example.productservice.services;

public interface CategoryService {
    String getAllCategories();

    String getProductsInCategory(Long categoryId);
}
