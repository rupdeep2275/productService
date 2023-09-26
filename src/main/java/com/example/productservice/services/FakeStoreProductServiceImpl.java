package com.example.productservice.services;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products",
                ProductDto[].class);

        List<Product> answer = new ArrayList<>();

        for(ProductDto productDto : l.getBody()) {
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());
            Category category = new Category();
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            answer.add(product);
        }
        return answer;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                ProductDto.class, productId);

        ProductDto productDto = response.getBody();

        Product product = new Product();
        product.setId(productDto.getId()); //why null ask Deepanshu
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());

        return product;
    }

    @Override
    public Product addNewProduct(ProductDto productDto){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<ProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products",
                productDto, ProductDto.class);

        ProductDto productDto1 = response.getBody();

        Product product1 = new Product();
        product1.setId(productDto1.getId());
        product1.setTitle(productDto1.getTitle());
        product1.setPrice(productDto1.getPrice());
        Category category = new Category();
        category.setName(productDto1.getCategory());
        product1.setCategory(category);
        product1.setImageUrl(productDto1.getImage());

        return product1;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        return null;
    }
}
