package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.CategoryService;
import com.example.productservice.utils.Convert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(@Qualifier(value = "selfCategoryService") CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public List<String> getAllCategories() throws NotFoundException {
        if (categoryService.getAllCategories().isEmpty()) {
            throw new NotFoundException("No categories found");
        }
        List<String> list = new ArrayList<>();
        for (Category category : categoryService.getAllCategories().get()) {
            list.add(category.getName());
        }
        return list;
    }

    @GetMapping("/{categoryName}")
    public List<ProductDto> getProductsInCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        Optional<List<Product>> optionalList = categoryService.getProductsInCategory(categoryName);
        if (optionalList.isEmpty()) {
            throw new NotFoundException("No products found in category " + categoryName);
        }
        List<ProductDto> list = new ArrayList<>();
        for (Product product : optionalList.get()) {
            list.add(Convert.ProductToProductDto(product));
        }
        return list;
    }
}
