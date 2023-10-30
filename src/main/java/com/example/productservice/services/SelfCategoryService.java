package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SelfCategoryService implements CategoryService{
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Optional<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(categories);
    }

    @Override
    public Optional<List<Product>> getProductsInCategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        if (products.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(products);
    }
}
