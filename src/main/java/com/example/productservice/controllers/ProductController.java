package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductDto;
import com.example.productservice.exceptions.NotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.services.ProductService;
import com.example.productservice.utils.Convert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    private ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }
    @GetMapping()
    public List<Product> getAllProducts() throws NotFoundException {
        if (productService.getAllProducts().isEmpty()) {
            throw new NotFoundException("No products found");
        }
        return productService.getAllProducts().get();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException{
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("auth-token", "noaccess4uheyhey");
        Optional<Product> productOptional = productService.getSingleProduct(productId);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product with id " + productId + " not found");
        }
        ResponseEntity<Product> response = new ResponseEntity<>(
                productOptional.get(),
                headers,
                HttpStatus.OK
        );
        return response;
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto) throws NotFoundException{
        Optional<Product> productOptional = productService.addNewProduct(productDto);
        if(productOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<Product> response = new ResponseEntity<>(productOptional.get(), HttpStatus.OK);

        return response;
    }

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) throws NotFoundException {
        Product product = Convert.ProductDtoToProduct(productDto);
        Optional<Product> productOptional = productService.updateProduct(productId, product);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + " not found");
        }
        return productOptional.get();
    }

    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) throws NotFoundException {
        Product product = Convert.ProductDtoToProduct(productDto);
        Optional<Product> productOptional = productService.replaceProduct(productId, product);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + " not found");
        }
        return productOptional.get();
    }

    @DeleteMapping("/{productId}")
    public Product deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Product> productOptional = productService.deleteProduct(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + " not found");
        }
        return productOptional.get();
    }
}
