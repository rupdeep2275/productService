package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping()
    public ResponseEntity<Category> addNewProduct(@RequestBody Category category) throws NotFoundException{
        Optional<Category> categoryOptional = categoryService.addNewCategory(category);
        if(categoryOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<Category> response = new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);

        return response;
    }

    @GetMapping("/{categoryName}")
    public List<Product> getProductsInCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        if (categoryService.getProductsInCategory(categoryName).isEmpty()) {
            throw new NotFoundException("No products found in category " + categoryName);
        }
        return categoryService.getProductsInCategory(categoryName).get();
    }
}
