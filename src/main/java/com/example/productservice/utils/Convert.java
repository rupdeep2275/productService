package com.example.productservice.utils;

import com.example.productservice.clients.fakestoreapi.FakeStoreProductDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;

public class Convert {
    public static Product FakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }

    public static FakeStoreProductDto ProductToFakeStoreProductDto(Product product) {
        FakeStoreProductDto productDto = new FakeStoreProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        return productDto;
    }

    public static Product ProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
//        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }

    public static ProductDto ProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
