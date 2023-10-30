package com.example.productservice.services;

import com.example.productservice.clients.fakestoreapi.FakeStoreClient;
import com.example.productservice.clients.fakestoreapi.FakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.utils.Convert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{

    private FakeStoreClient fakeStoreClient;

    public FakeStoreCategoryServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }
    @Override
    public Optional<List<Category>> getAllCategories() {
        List<String> categories = fakeStoreClient.getAllCategories();
        if (categories == null) {
            return Optional.empty();
        }
        List<Category> answer = new ArrayList<>();
        for(String str : categories) {
            Category category = new Category();
            category.setName(str);
            answer.add(category);
        }
        return Optional.of(answer);
    }

    @Override
    public Optional<List<Product>> getProductsInCategory(String categoryName) {
        List<FakeStoreProductDto> productDtos = fakeStoreClient.getProductsInCategory(categoryName);
        if (productDtos == null) {
            return Optional.empty();
        }
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDto productDto : productDtos) {
            answer.add(Convert.FakeStoreProductDtoToProduct(productDto));
        }
        return Optional.of(answer);
    }
}
