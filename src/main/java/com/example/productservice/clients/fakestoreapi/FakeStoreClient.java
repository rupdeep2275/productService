package com.example.productservice.clients.fakestoreapi;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Product;
import com.example.productservice.utils.Convert;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        return Arrays.asList(l.getBody());
    }

    public FakeStoreProductDto getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class, productId);
        FakeStoreProductDto productDto = response.getBody();
        return productDto;
    }

    public FakeStoreProductDto addNewProduct(ProductDto productDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products",
                productDto, FakeStoreProductDto.class);
        FakeStoreProductDto productDto1 = response.getBody();
        return productDto1;
    }

    public FakeStoreProductDto updateProduct(Long productId, Product product){
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        FakeStoreProductDto fakeStoreProductDto = Convert.ProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.PATCH, new HttpEntity<FakeStoreProductDto>(fakeStoreProductDto),
                FakeStoreProductDto.class, productId);
        FakeStoreProductDto productDto = response.getBody();
        return productDto;
    }

    public FakeStoreProductDto replaceProduct(Long productId, Product product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = Convert.ProductToFakeStoreProductDto(product);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT, new HttpEntity<FakeStoreProductDto>(fakeStoreProductDto),
                FakeStoreProductDto.class, productId);
        FakeStoreProductDto productDto = response.getBody();
        return productDto;
    }

    public FakeStoreProductDto deleteProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                HttpMethod.DELETE, null, FakeStoreProductDto.class, productId);
        FakeStoreProductDto productDto = response.getBody();
        return productDto;
    }

    public List<String> getAllCategories(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
                String[].class);
        return Arrays.asList(l.getBody());
    }

    public List<FakeStoreProductDto> getProductsInCategory(String categoryName){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products/category/{categoryName}",
                FakeStoreProductDto[].class, categoryName);
        return Arrays.asList(response.getBody());
    }
}
