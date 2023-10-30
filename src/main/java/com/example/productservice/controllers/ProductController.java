package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.services.ProductService;
import com.example.productservice.utils.Convert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    private ProductRepository productRepository;

    public ProductController(@Qualifier(value = "selfProductService") ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }
    @GetMapping()
    public List<ProductDto> getAllProducts() throws NotFoundException {
        if (productService.getAllProducts().isEmpty()) {
            throw new NotFoundException("No products found");
        }
        List<ProductDto> list = new ArrayList<>();
        for(Product product: productService.getAllProducts().get()){
            list.add(Convert.ProductToProductDto(product));
        }
        return list;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException{
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccess4uheyhey");
        Optional<Product> productOptional = productService.getSingleProduct(productId);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product with id " + productId + " not found");
        }
        Product product = productOptional.get();
        ResponseEntity<ProductDto> response = new ResponseEntity<>(
                Convert.ProductToProductDto(product),
                headers,
                HttpStatus.OK
        );
        return response;
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto) throws NotFoundException{
        Optional<Product> productOptional = productService.addNewProduct(Convert.ProductDtoToProduct(productDto));
        if(productOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product product = productOptional.get();
        ResponseEntity<ProductDto> response = new ResponseEntity<>(Convert.ProductToProductDto(product), HttpStatus.OK);

        return response;
    }

    @PatchMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) throws NotFoundException {
        Product product = Convert.ProductDtoToProduct(productDto);
        Optional<Product> productOptional = productService.updateProduct(productId, product);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + " not found");
        }
        return Convert.ProductToProductDto(productOptional.get());
    }

    @PutMapping("/{productId}")
    public ProductDto replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) throws NotFoundException {
        Product product = Convert.ProductDtoToProduct(productDto);
        Optional<Product> productOptional = productService.replaceProduct(productId, product);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + " not found");
        }
        return Convert.ProductToProductDto(productOptional.get());
    }

    @DeleteMapping("/{productId}")
    public ProductDto deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Product> productOptional = productService.deleteProduct(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + " not found");
        }
        return Convert.ProductToProductDto(productOptional.get());
    }
}
