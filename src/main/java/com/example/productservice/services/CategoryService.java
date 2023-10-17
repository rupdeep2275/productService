package com.example.productservice.services;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface CategoryService {
    Optional<List<Category>> getAllCategories();

    Optional<Category> addNewCategory(Category category);

    Optional<List<Product>> getProductsInCategory(String categoryName);
}
