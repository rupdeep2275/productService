package com.example.productservice.services;

import com.example.productservice.clients.fakestoreapi.FakeStoreClient;
import com.example.productservice.clients.fakestoreapi.FakeStoreProductDto;
import com.example.productservice.models.Product;
import com.example.productservice.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FakeStoreProductServiceImpl implements ProductService{

    private final FakeStoreClient fakeStoreClient;
//    private Map<Long, FakeStoreProductDto> fakeStoreProducts = new HashMap<>();

    private final RedisTemplate<Long, Object> redisTemplate;

    @Override
    public Page<Product> getProducts(int numberOfProducts, int offset) {
        return null;
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
//        if(fakeStoreProducts.containsKey(productId)) {
//            return Optional.of(Convert.FakeStoreProductDtoToProduct(fakeStoreProducts.get(productId)));
//        }
        FakeStoreProductDto redisProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get(productId, "PRODUCTS");
        if(redisProductDto != null) {
            return Optional.of(Convert.FakeStoreProductDtoToProduct(redisProductDto));
        }
        FakeStoreProductDto productDto = fakeStoreClient.getSingleProduct(productId);
//        fakeStoreProducts.put(productId, productDto);
        redisTemplate.opsForHash().put(productId, "PRODUCTS", productDto);
        if(productDto == null) {
            return Optional.empty();
        }
        return Optional.of(Convert.FakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Optional<Product> addNewProduct(Product product){
        FakeStoreProductDto productDto1 = fakeStoreClient.addNewProduct(Convert.ProductToProductDto(product));
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
