package com.example.productservice.services;

import com.example.productservice.clients.fakestoreapi.FakeStoreClient;
import com.example.productservice.clients.fakestoreapi.FakeStoreProductDto;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Product;
import com.example.productservice.utils.Convert;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class FakeStoreProductServiceImpl implements ProductService{

    private FakeStoreClient fakeStoreClient;

    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        if (fakeStoreProductDtos == null) {
            return Optional.empty();
        }
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDto productDto : fakeStoreProductDtos) {
            answer.add(Convert.FakeStoreProductDtoToProduct(productDto));
        }
        return Optional.of(answer);
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        FakeStoreProductDto productDto = fakeStoreClient.getSingleProduct(productId);
        if(productDto == null) {
            return Optional.empty();
        }
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Optional<Product> addNewProduct(ProductDto productDto){
        FakeStoreProductDto productDto1 = fakeStoreClient.addNewProduct(productDto);
        if(productDto1 == null) {
            return Optional.empty();
        }
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDto1));
    }

    @Override
    public Optional<Product> updateProduct(Long productId, Product product) {
        FakeStoreProductDto productDto = fakeStoreClient.updateProduct(productId, product);
        if(productDto == null) {
            return Optional.empty();
        }
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Optional<Product> replaceProduct(Long productId, Product product) {
        FakeStoreProductDto productDto = fakeStoreClient.replaceProduct(productId, product);
        if (productDto == null) {
            return Optional.empty();
        }
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) {
        FakeStoreProductDto productDto = fakeStoreClient.deleteProduct(productId);
        if (productDto == null) {
            return Optional.empty();
        }
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDto));
    }
}
