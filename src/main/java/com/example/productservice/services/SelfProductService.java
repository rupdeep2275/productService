package com.example.productservice.services;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Product> getProducts(int numberOfProducts, int offset) {
        Page<Product> products = productRepository.findAll(
                PageRequest.of(offset/numberOfProducts, numberOfProducts)
        );
        return products;
    }
    @Override
    public Optional<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(products);
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if(product == null) {
            return Optional.empty();
        }
        return Optional.of(product);
    }

    @Override
    public Optional<Product> addNewProduct(Product newProduct) {
        Category category = categoryRepository.findCategoryByName(newProduct.getCategory().getName());
        if(category != null){
            newProduct.setCategory(category);
        }
        Product product = productRepository.save(newProduct);
        return Optional.of(product);
    }

    @Override
    public Optional<Product> updateProduct(Long productId, Product product) {
        Product prod = productRepository.findById(productId).orElse(null);
        if(prod == null) {
            return Optional.empty();
        }
        if(product.getCategory() != null){
            Category dbCategory = categoryRepository.findCategoryByName(product.getCategory().getName());
            if (dbCategory == null) {
                dbCategory = categoryRepository.save(product.getCategory());
            }
            prod.setCategory(dbCategory);
        }
        if(product.getTitle() != null) {
            prod.setTitle(product.getTitle());
        }
        if(product.getPrice() != 0) {
            prod.setPrice(product.getPrice());
        }
        if(product.getDescription() != null) {
            prod.setDescription(product.getDescription());
        }
        if(product.getImageUrl() != null) {
            prod.setImageUrl(product.getImageUrl());
        }
        productRepository.save(prod);
        return Optional.of(prod);
    }

    @Override
    public Optional<Product> replaceProduct(Long productId, Product product) {
        Product prod = productRepository.findById(productId).orElse(null);
        if(prod == null) {
            return Optional.empty();
        }
        Category dbCategory = categoryRepository.findCategoryByName(product.getCategory().getName());
        if (dbCategory == null) {
            dbCategory = categoryRepository.save(product.getCategory());
        }
        prod.setCategory(dbCategory);
        prod.setTitle(product.getTitle());
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        prod.setImageUrl(product.getImageUrl());
        productRepository.save(prod);
        return Optional.of(prod);
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) {
        Product product = getSingleProduct(productId).orElse(null);
        if(product == null) {
            return Optional.empty();
        }
        productRepository.deleteById(productId);
        return Optional.of(product);
    }
}
