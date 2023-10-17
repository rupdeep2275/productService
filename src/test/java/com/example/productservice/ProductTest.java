package com.example.productservice;
import com.example.productservice.dtos.ProductDto;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.services.SelfProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductTest {
    @Autowired
    private SelfProductService selfProductService;

    @Test
    void checkWorkingFine(){
        Product product = selfProductService.deleteProduct(452L).get();
        System.out.println(product);

    }
}
