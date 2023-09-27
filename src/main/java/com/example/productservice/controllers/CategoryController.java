package com.example.productservice.controllers;

import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public List<Category> getAllCategories() throws NotFoundException {
        if (categoryService.getAllCategories().isEmpty()) {
            throw new NotFoundException("No categories found");
        }
        return categoryService.getAllCategories().get();
    }

    @GetMapping("/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        if (categoryService.getProductsInCategory(categoryName).isEmpty()) {
            throw new NotFoundException("No products found in category " + categoryName);
        }
        return categoryService.getProductsInCategory(categoryName).get();
    }
}
